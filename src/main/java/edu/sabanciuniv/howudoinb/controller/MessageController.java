package edu.sabanciuniv.howudoinb.controller;

import edu.sabanciuniv.howudoinb.model.MessageModel;
import edu.sabanciuniv.howudoinb.model.UserModel;
import edu.sabanciuniv.howudoinb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;

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
	public int getMessages(@RequestBody UserModel receivers) {
		//TODO same things

		return 0;
		//return messageService.getMessages();
	}
}
