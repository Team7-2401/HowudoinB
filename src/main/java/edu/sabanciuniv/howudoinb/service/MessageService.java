package edu.sabanciuniv.howudoinb.service;

import edu.sabanciuniv.howudoinb.model.MessageModel;
import edu.sabanciuniv.howudoinb.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

//	public String sendMessage(MessageModel message) {
	//TODO set current time (overwrite)
	// TODO add message to database,
//	}

//	public MessageModel getMessageById(int id) {
//	return messageRepository.findById(id).orElse(null);
//	}
//
//	public MessageModel saveMessage(MessageModel message) {
//	return messageRepository.save(message);
//	}
//
//	public void deleteMessage(int id) {
//	messageRepository.deleteById(id);
//	}
}
