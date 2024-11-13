package edu.sabanciuniv.howudoinb.service;

import edu.sabanciuniv.howudoinb.model.FriendModel;
import edu.sabanciuniv.howudoinb.model.MessageModel;
import edu.sabanciuniv.howudoinb.model.UserModel;
import edu.sabanciuniv.howudoinb.repository.MessageRepository;
import edu.sabanciuniv.howudoinb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserRepository userRepository;

	public int sendMessage(MessageModel message) {

		//Check that receivers are in the sender's friend list
		int check = receiversAreFriends(message);
		if (check != 0) {
			return check;
		}
		//add message to database and return status
		messageRepository.save(message);
		return 0;
	}

	public int receiversAreFriends(MessageModel message) {
		UserModel sender = message.getSender();
		//lookup the sender's friend list from the repo
		List<UserModel> result = userRepository.findByEmail(sender.getEmail());
		if(result.size() != 1){
			return 7;
		}
		ArrayList<FriendModel> friends = result.getFirst().getFriends();

		for (UserModel receiver : message.getReceivers()) {
			boolean found = false;
			for (FriendModel friend : friends) {
				//also check if they're accepted
				if (friend.getEmail().equals(receiver.getEmail()) &&
				friend.getStatus().equals("accepted")) {
					found = true;
					break;
				}
			}
			if (!found) {
				return 6;
			}
		}
		return 0;
	}
}
