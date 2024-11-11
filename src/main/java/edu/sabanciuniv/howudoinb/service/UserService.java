package edu.sabanciuniv.howudoinb.service;

import edu.sabanciuniv.howudoinb.model.UserModel;
import edu.sabanciuniv.howudoinb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserModel getUserById(int id) {
	return userRepository.findById(id).orElse(null);
	}

	public UserModel saveUser(UserModel user) {
	return userRepository.save(user);
	}

	public void deleteUser(int id) {
	userRepository.deleteById(id);
	}
}
