package edu.sabanciuniv.howudoinb.service;

import edu.sabanciuniv.howudoinb.model.FriendModel;
import edu.sabanciuniv.howudoinb.model.UserModel;
import edu.sabanciuniv.howudoinb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

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

	public String addFriend(UserModel user, String email){
		List<UserModel> usersFromDB = userRepository.findByEmail(user.getEmail());
		if (usersFromDB == null || usersFromDB.size() != 1) {
			return "Friend not found";
		}
		UserModel userFromDB = usersFromDB.getFirst(); //friend

		List<UserModel> usersFromDB2 = userRepository.findByEmail(email);
		UserModel userFromDB2 = usersFromDB2.getFirst();

		ArrayList<FriendModel> userFriends = userFromDB2.getFriends();
		ArrayList<FriendModel> friendFriends = userFromDB.getFriends();

		//add each email to the other user's friends list with status pending
		FriendModel newFriend = new FriendModel();
		newFriend.setEmail(userFromDB.getEmail());
		newFriend.setStatus("pending");
		userFriends.add(newFriend);

		FriendModel newFriend2 = new FriendModel();
		newFriend2.setEmail(userFromDB2.getEmail());
		newFriend2.setStatus("pending");
		friendFriends.add(newFriend2);
		// Update both users' friends list in the repository
		userRepository.updateFriendsByEmail(userFromDB.getEmail(), friendFriends);
		userRepository.updateFriendsByEmail(userFromDB2.getEmail(), userFriends);
		return "Friend request sent";
		/* function adds friend to user's friend list */
		//TODO: add friend request to both users' friends with appropriate status
//		return -1;
	}

	//TODO function for friends accept
	//TODO check that the friend request is in the friends list
	//TODO change status on both sender and receiver to accepted

	//TODO function for return friend list
	//TODO go repository, find by email, find first and last names, return friends list (names) (only the accepted friends)

//	public UserModel getUserById(int id) {
//	return userRepository.findById(id).orElse(null);
//	}
//
//	public UserModel saveUser(UserModel user) {
//	return userRepository.save(user);
//	}
//
//	public void deleteUser(int id) {
//	userRepository.deleteById(id);
//	}
}
