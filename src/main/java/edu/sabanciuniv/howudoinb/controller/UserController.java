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
		if(validationResponse == 0){
			return userService.registerUser(user);
		}
		else{
			return validationResponse;
		}
	}

	@PostMapping("/login")
	public String loginUser(@RequestBody UserModel user) {
		int validationResponse = user.loginValidate();
		if(validationResponse == 0){
			return userService.verifyUser(user);
		}
		return "failed to validate user";
	}

@PostMapping("/friends/add")
public String addFriend(@RequestHeader("Authorization") String token, @RequestBody UserModel user) {

	// Extract the token if it contains a prefix like "Bearer "
    String actualToken = token.startsWith("Bearer ") ? token.substring(7) : null;
	String email =  userService.whoSent(actualToken);

	//TODO: verify passed user object (it must contain email)
    // TODO: pass verified object to userService to add friend request
	// TODO: return status
}

	@PostMapping("/friends/accept")
	public String acceptFriend(@RequestBody UserModel user) {
		//TODO find sending user
		// Validate user just email
		//TODO Pass to userService
	}

	@GetMapping("/friends")
	public ArrayList<FriendModel> getFriends() {
		//TODO get sending user
		//send to user service
	}
}
