package edu.sabanciuniv.howudoinb.controller;

import edu.sabanciuniv.howudoinb.model.MessageModel;
import edu.sabanciuniv.howudoinb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;

	@PostMapping("/messages/send")
	public MessageModel sendMessage(@RequestBody MessageModel message) {
		return messageService.saveMessage(message);
	}

	@GetMapping("/messages")
	public MessageModel getMessages() {
		return messageService.getMessages();
	}
}
