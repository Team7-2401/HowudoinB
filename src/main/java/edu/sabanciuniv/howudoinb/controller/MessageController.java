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
		//TODO validate message structure (receivers and message)
		//TODO pass to message service
		//TODO return sent
		return 0;
		//return messageService.sendMessage(message);
		//return messageService.saveMessage(message);
	}

	@GetMapping("/messages")
	public MessageModel getMessages(@RequestBody UserModel receivers) {
		//TODO same things

		return messageService.getMessages();
	}
}
