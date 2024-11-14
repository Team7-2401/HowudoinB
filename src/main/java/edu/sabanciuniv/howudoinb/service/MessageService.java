package edu.sabanciuniv.howudoinb.service;

import edu.sabanciuniv.howudoinb.model.FriendModel;
import edu.sabanciuniv.howudoinb.model.GroupModel;
import edu.sabanciuniv.howudoinb.model.MessageModel;
import edu.sabanciuniv.howudoinb.model.UserModel;
import edu.sabanciuniv.howudoinb.repository.GroupRepository;
import edu.sabanciuniv.howudoinb.repository.MessageRepository;
import edu.sabanciuniv.howudoinb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GroupRepository groupRepository;

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

	public ArrayList<MessageModel> getMessages(String receiver, String email) {
		/* this function finds the conversation between sender and receiver */
		System.out.println("got here");
		//retrieve messages from the database
        List<MessageModel> sent = messageRepository.findByReceivers_EmailAndSender_Email(receiver, email);
		List<MessageModel> recieved = messageRepository.findByReceivers_EmailAndSender_Email(email, receiver);

        //combine sent and received and sort by timestamp
		ArrayList<MessageModel> conversation = new ArrayList<>(sent);
		conversation.addAll(recieved);
		conversation.sort((m1, m2) -> m1.getTimestamp().compareTo(m2.getTimestamp()));
		return conversation;
	}

	//group messages ---------------------------------------------------------------------------------------------------

	public int sendGroupMessage(int groupId, MessageModel message, String email) {
		//check if email is in group
		UserModel user = userRepository.findByEmail(email).getFirst();
		//remove sensitive information
		user.setPassword("");
		user.setFriends(new ArrayList<FriendModel>());

		//set email as sender
		message.setSender(user);

		//check if groupid is valid and set groupid
		GroupModel group = groupRepository.findByGroupid(groupId);

		if (group == null) {
			return -1;
		} else {
			message.setGroupid(group.getGroupid());
		}

		//get group members, remove sender and set as recievers
		List<UserModel> members = group.getMembers();
		members.remove(user);
		message.setReceivers(members);

		//add message to database
		messageRepository.save(message);
		return 0;
	}

	public ArrayList<MessageModel> getGroupMessages(int groupId, String email) {
		//check if group is valid
		GroupModel group = groupRepository.findByGroupid(groupId);
		if (group == null) {
			return null;
		}

		//check if email is in group
		boolean found = false;
		for (UserModel member : group.getMembers()) {
			if (member.getEmail().equals(email)) {
				found = true;
				break;
			}
		}
		if (!found) {
			return null;
		}

		//retrieve messages from the database where email is either sender or receiver
		List<MessageModel> messages = messageRepository.findByGroupid(groupId);
		//filter out messages that the user is not a part of (sender or receiver)
		ArrayList<MessageModel> conversation = new ArrayList<>();
		for (MessageModel message : messages) {
			if (message.getSender().getEmail().equals(email)) {
				conversation.add(message);
			} else {
				for (UserModel receiver : message.getReceivers()) {
					if (receiver.getEmail().equals(email)) {
						conversation.add(message);
						break;
					}
				}
			}
		}
		//sort by timestamp
		conversation.sort(Comparator.comparing(MessageModel::getTimestamp));
		return conversation;
	}
}
