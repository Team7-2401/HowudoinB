package edu.sabanciuniv.howudoinb.service;

import edu.sabanciuniv.howudoinb.model.FriendModel;
import edu.sabanciuniv.howudoinb.model.GroupModel;
import edu.sabanciuniv.howudoinb.model.UserModel;
import edu.sabanciuniv.howudoinb.repository.GroupRepository;
import edu.sabanciuniv.howudoinb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    public int createGroup(GroupModel group, String email) {

        //set as the group creator
        UserModel user = userRepository.findByEmail(email).getFirst();
        //remove the sensitive data
        user.setFriends(new ArrayList<FriendModel>());
        user.setPassword("");

        group.setCreatedby(user);

        //if there is a list of members, make sure they exist
        if (group.getMembers() != null){
            for (int i = 0; i < group.getMembers().size(); i++){
                if (userRepository.findByEmail(group.getMembers().get(i).getEmail()).size() != 1){
                    return -1;
                }
            }
        }

        //save group and return id
        groupRepository.save(group);

        return group.getGroupid();
    }

//    public GroupModel getGroupById(int id) {
//	return groupRepository.findById(id).orElse(null);
//    }
//
//    public GroupModel saveGroup(GroupModel group) {
//	return groupRepository.save(group);
//    }
//
//    public void deleteGroup(int id) {
//	groupRepository.deleteById(id);
//    }
}
