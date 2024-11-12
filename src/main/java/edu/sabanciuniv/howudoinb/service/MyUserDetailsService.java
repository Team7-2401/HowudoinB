package edu.sabanciuniv.howudoinb.service;

import edu.sabanciuniv.howudoinb.model.UserModel;
import edu.sabanciuniv.howudoinb.model.UserPrincipal;
import edu.sabanciuniv.howudoinb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserModel> users = userRepository.findByEmail(username);
        if (users.size() != 1) {
            throw new UsernameNotFoundException("User not found");
        }
        UserModel user = userRepository.findByEmail(username).get(0);

        return new UserPrincipal(user);
    }
}
