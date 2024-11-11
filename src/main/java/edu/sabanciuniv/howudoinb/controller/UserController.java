package edu.sabanciuniv.howudoinb.controller;

import edu.sabanciuniv.howudoinb.model.UserModel;
import edu.sabanciuniv.howudoinb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/{id}")
	public UserModel getUserById(@PathVariable int id) {
	return userService.getUserById(id);
	}

	@PostMapping("/user")
	public UserModel saveUser(@RequestBody UserModel user) {
	return userService.saveUser(user);
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id) {
	userService.deleteUser(id);
	}
}
