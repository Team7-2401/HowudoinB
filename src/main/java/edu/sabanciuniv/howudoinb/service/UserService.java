package edu.sabanciuniv.howudoinb.service;

import edu.sabanciuniv.howudoinb.model.FriendModel;
import edu.sabanciuniv.howudoinb.model.UserModel;
import edu.sabanciuniv.howudoinb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	private JWTService jwtService;

	public int registerUser(UserModel user) {
		/* function adds user to database */
		userRepository.save(user);
		return 0;
	}

	public String verifyUser(UserModel user) {
		Authentication authentication =
				authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

		if(authentication.isAuthenticated()) {
			System.out.println("User:  " + user.getEmail() +"  logged in");
			return jwtService.generateToken(user.getEmail());
		}
		else {
			System.out.println("User:  " + user.getEmail() +"  failed to log in");
			return "Failed to log in";
		}
	}

	public String whoSent(String token) {
		return jwtService.extractUserName(token);
	}

	//TODO Test it again. The adding friends but they someone already sent.
	public String addFriend(UserModel user, String email){

		// Check if the user is trying to add themselves
		if (user.getEmail().equals(email)) {
			return "You cannot add yourself as a friend";
		}

		List<UserModel> usersFromDB = userRepository.findByEmail(user.getEmail());

		if (usersFromDB == null || usersFromDB.size() != 1) {
			return "Friend not found";
		}

		//Check if they are already friends or if the user is trying to add themselves
		UserModel userFromDB = usersFromDB.getFirst(); //friend

		List<UserModel> usersFromDB2 = userRepository.findByEmail(email);
		UserModel userFromDB2 = usersFromDB2.getFirst();

		ArrayList<FriendModel> userFriends = userFromDB2.getFriends();
		ArrayList<FriendModel> friendFriends = userFromDB.getFriends();

		// Check if they are already friends
		boolean alreadyFriends = userFriends.stream()
				.anyMatch(friend -> friend.getEmail().equals(user.getEmail()) && friend.getStatus().equals("accepted"));

		if (alreadyFriends) {
			return "You are already friends";
		}

		// Check if there is a pending friend request from the other user
		boolean requestExists = userFriends.stream()
				.anyMatch(friend -> friend.getEmail().equals(user.getEmail()) && friend.getStatus().equals("pending"));

		if (requestExists) {
			// Change status on both sender and receiver to accepted
			userRepository.updateFriendStatusToAccepted(email, user.getEmail());
			userRepository.updateFriendStatusToAccepted(user.getEmail(), email);
			return "Friend request accepted";
		}

		//add each email to the other user's friends list with status pending
		FriendModel newFriend = new FriendModel();
		newFriend.setEmail(userFromDB.getEmail());
		newFriend.setStatus("waiting");
		userFriends.add(newFriend);

		FriendModel newFriend2 = new FriendModel();
		newFriend2.setEmail(userFromDB2.getEmail());
		newFriend2.setStatus("pending");
		friendFriends.add(newFriend2);
		// Update both users' friends list in the repository
		userRepository.updateFriendsByEmail(userFromDB.getEmail(), friendFriends);
		userRepository.updateFriendsByEmail(userFromDB2.getEmail(), userFriends);
		return "Friend request sent";
	}

	//Function for friends accept
	public String acceptFriend(UserModel user, String email){

		// Retrieve the user who is accepting the friend request
		List<UserModel> usersFromDB = userRepository.findByEmail(email);
		UserModel userFromDB = usersFromDB.getFirst();


		//Check if these is even a request

		//check that the friend request is in the friends list

		// Check if they are already friends
		boolean alreadyFriends = userFromDB.getFriends().stream()
				.anyMatch(friend -> friend.getEmail().equals(user.getEmail()) && friend.getStatus().equals("accepted"));

		if (alreadyFriends) {
			return "You are already friends";
		}

		// Check if there is a pending friend request
		boolean requestExists = userFromDB.getFriends().stream()
				.anyMatch(friend -> friend.getEmail().equals(user.getEmail()) && friend.getStatus().equals("pending"));

		if (!requestExists) {
			return "No pending friend request found";
		}


		//change status on both sender and receiver to accepted

		// Update the status for the accepting user
		userRepository.updateFriendStatusToAccepted(email, user.getEmail());

		// Update the status for the friend who sent the request
		userRepository.updateFriendStatusToAccepted(user.getEmail(), email);

		return "Friend request accepted";
	}


	//Function for return friend list
	public ArrayList<FriendModel>getFriends(String email){
		// Find the user

		// Retrieve the user who is accepting the friend request
		List<UserModel> usersFromDB = userRepository.findByEmail(email);
		UserModel userFromDB = usersFromDB.getFirst();

		ArrayList<FriendModel> friends = userFromDB.getFriends();
		// Add this print statement after line 160
		System.out.println("Friends: " + friends);


		//Check if the user has any friends
		if (friends== null || friends.isEmpty()) {
			//Return an empty list if no friends
			return null;
		}

		//Filter accepted friends.
		// Filter accepted friends and add email
		ArrayList<FriendModel> acceptedFriends = new ArrayList<>();
		for (FriendModel friend : friends) {
			if ("accepted".equals(friend.getStatus())) {
				friend.setEmail(friend.getEmail());
				acceptedFriends.add(friend);
			}
		}

		//Set the name and last name of the friends.
		for(int i = 0; i < acceptedFriends.size(); i++) {
			UserModel friendFromDB = userRepository.findByEmail(acceptedFriends.get(i).getEmail()).getFirst();
			acceptedFriends.get(i).setName(friendFromDB.getName());
			acceptedFriends.get(i).setLastName(friendFromDB.getLastName());
		}


		//Return list of friends
		//Go repository, find by email, find first and last names, return friends list (names) (only the accepted friends)
		return acceptedFriends;

	}
}
