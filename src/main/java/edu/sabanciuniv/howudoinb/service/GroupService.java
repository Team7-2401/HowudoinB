package edu.sabanciuniv.howudoinb.service;

import edu.sabanciuniv.howudoinb.model.FriendModel;
import edu.sabanciuniv.howudoinb.model.GroupModel;
import edu.sabanciuniv.howudoinb.model.UserModel;
import edu.sabanciuniv.howudoinb.repository.GroupRepository;
import edu.sabanciuniv.howudoinb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        //add the creator to the members list if he's not there
        if (group.getMembers() == null || group.getMembers().stream().noneMatch(userModel -> userModel.getEmail().equals(email))){
            if(group.getMembers() == null){
                group.setMembers(new ArrayList<UserModel>());
            }
            List<UserModel> members = group.getMembers();
            members.add(user);
            group.setMembers(members);
        }

        //save group and return id
        groupRepository.save(group);

        return group.getGroupid();
    }

    public int addMember(int groupId, UserModel newMember, String email) {
        //check if group exists and get it
        GroupModel group = groupRepository.findByGroupid(groupId);
        if (group == null){
            return 1;
        }

        //check if user (requester) is in the group
        if ( group.getMembers().stream().noneMatch(user -> user.getEmail().equals(email))){
            return 2;
        }


        //check if user (to-be-added) exists
        if (userRepository.findByEmail(newMember.getEmail()).size() != 1){
            return 3;
        } else {
            //find the user and remove sensitive data
            newMember = userRepository.findByEmail(newMember.getEmail()).get(0);
            newMember.setFriends(new ArrayList<FriendModel>());
            newMember.setPassword("");
        }

        //get userList from group, update it and update the repo
        List<UserModel> members = group.getMembers();
        members.add(newMember);
        group.setMembers(members);

        groupRepository.updateMembersByGroupid(groupId, members);

        return 0;
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
