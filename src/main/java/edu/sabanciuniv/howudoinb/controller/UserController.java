package edu.sabanciuniv.howudoinb.controller;

import edu.sabanciuniv.howudoinb.model.UserModel;
import edu.sabanciuniv.howudoinb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public int registerUser(@RequestBody UserModel user) {
		int validationResponse = user.validateUser();
		if(validationResponse == 0){
			return userService.registerUser(user);
		}
		else{
			return validationResponse;
		}
	}

	@PostMapping("/login")
	public UserModel loginUser(@RequestBody UserModel user) {
		return userService.getUser(user);
	}

	@PostMapping("/friends/add")
	public UserModel addFriend(@RequestBody UserModel user) {
		return userService.saveUser(user);
	}

	@PostMapping("/friends/accept")
	public UserModel acceptFriend(@RequestBody UserModel user) {
		return userService.saveUser(user);
	}

	@GetMapping("/friends")
	public UserModel getFriends() {
		return userService.getFriends();
	}
}
