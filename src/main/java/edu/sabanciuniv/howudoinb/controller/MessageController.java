package edu.sabanciuniv.howudoinb.controller;

import edu.sabanciuniv.howudoinb.model.FriendModel;
import edu.sabanciuniv.howudoinb.model.MessageModel;
import edu.sabanciuniv.howudoinb.model.UserModel;
import edu.sabanciuniv.howudoinb.service.MessageService;
import edu.sabanciuniv.howudoinb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;

	@PostMapping("/messages/send")
	public int sendMessage(@RequestBody MessageModel message) {
		//Validation
		int validation = message.validateSend();
		if (validation != 0) {
			return validation;
		}

		return messageService.sendMessage(message);
	}

	@GetMapping("/messages")
	public ArrayList<MessageModel> getMessages(@RequestHeader("Authorization") String token ,@RequestBody UserModel receiver) {

		//validate receiver
		int check = receiver.senderValidate(); //maybe this function should've been named messageValidate
		if (check != 0) {
			return null;
		}

		//find sender email
		String actualToken = token.startsWith("Bearer ") ? token.substring(7) : null;
		String email =  userService.whoSent(actualToken);

		//pass to service
		return messageService.getMessages(receiver.getEmail(), email);

	}
}
