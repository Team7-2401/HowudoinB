package edu.sabanciuniv.howudoinb.service;

import edu.sabanciuniv.howudoinb.model.UserModel;
import edu.sabanciuniv.howudoinb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

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

	public int addFriend(UserModel user) {
//		/* function adds friend to user's friend list */
//		List<UserModel> userFromDB = userRepository.findByEmail(user.getEmail());
//		if (userFromDB != null && userFromDB.size() == 1) {
//			UserModel userDB = userFromDB.getFirst();
//			userDB.getFriends().add(user.getFriends().get(0));
//			userRepository.save(userDB);
//			return 0;
//		}
		//TODO: add friend request to both users' friends with appropriate status
		return -1;
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
