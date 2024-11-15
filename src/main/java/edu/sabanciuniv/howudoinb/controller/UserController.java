package edu.sabanciuniv.howudoinb.controller;

import edu.sabanciuniv.howudoinb.model.FriendModel;
import edu.sabanciuniv.howudoinb.model.UserModel;
import edu.sabanciuniv.howudoinb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public int registerUser(@RequestBody UserModel user) {

		//Can a recently registered user have friends on registration?
		int validationResponse = user.validateUser();
		if (validationResponse == 0) {
			return userService.registerUser(user);
		}
		else {
			return validationResponse;
		}
	}

	@PostMapping("/login")
	public String loginUser(@RequestBody UserModel user) {
		int validationResponse = user.loginValidate();
		if (validationResponse == 0) {
			return userService.verifyUser(user);
		}
		return "failed to validate user";
	}

	@PostMapping("/friends/add")
	public String addFriend(@RequestHeader("Authorization") String token, @RequestBody UserModel user) {
		// Extract the token if it contains a prefix like "Bearer "
		String actualToken = token.startsWith("Bearer ") ? token.substring(7) : null;
		String email = userService.whoSent(actualToken);
		int validationResponse = user.friendValidate();
		if (validationResponse == 0) {
			return userService.addFriend(user, email);
		}
		return "failed to validate friend";
	}

     @PostMapping("/friends/accept")
	 public String acceptFriend(@RequestHeader("Authorization") String token, @RequestBody UserModel user){

		String actualToken = token.startsWith("Bearer ") ? token.substring(7) : null;

		 // Find sending user
		String email = userService.whoSent(actualToken);
		// Validate user just email
		 int validationResponse = user.friendValidate();
		 if (validationResponse == 0){

			 //Pass to userService
			 return userService.acceptFriend(user, email);
		 }
		 return "Unable to validate the friend. Ensure the email is correct and associated with a pending friend request.";
    }

	@GetMapping("/friends")
	public ArrayList<FriendModel>getFriends(@RequestHeader("Authorization")String token){

		String actualToken=token.startsWith("Bearer ")?token.substring(7):null;

		//Get sending user
		// Find sending user
		String email = userService.whoSent(actualToken);

		//send to user service
		return userService.getFriends(email);
	}
}