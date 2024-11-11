package edu.sabanciuniv.howudoinb.controller;

import edu.sabanciuniv.howudoinb.model.MessageModel;
import edu.sabanciuniv.howudoinb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;

	@GetMapping("/message/{id}")
	public MessageModel getMessageById(@PathVariable int id) {
	return messageService.getMessageById(id);
	}

	@PostMapping("/message")
	public MessageModel saveMessage(@RequestBody MessageModel message) {
	return messageService.saveMessage(message);
	}

	@DeleteMapping("/message/{id}")
	public void deleteMessage(@PathVariable int id) {
	messageService.deleteMessage(id);
	}
}
