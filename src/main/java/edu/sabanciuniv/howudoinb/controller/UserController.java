package edu.sabanciuniv.howudoinb.controller;

import edu.sabanciuniv.howudoinb.model.UserModel;
import edu.sabanciuniv.howudoinb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public int registerUser(@Valid @RequestBody UserModel user) {
		return userService.; //TODO: Implement this method
	}

	@PostMapping("/login")
	public UserModel loginUser(@Valid @RequestBody UserModel user) {
		return userService.getUser(user);
	}

	@PostMapping("/friends/add")
	public UserModel addFriend(@Valid @RequestBody UserModel user) {
		return userService.saveUser(user);
	}

	@PostMapping("/friends/accept")
	public UserModel acceptFriend(@Valid @RequestBody UserModel user) {
		return userService.saveUser(user);
	}

	@GetMapping("/friends")
	public UserModel getFriends() {
		return userService.getFriends();
	}
}
