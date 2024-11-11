package edu.sabanciuniv.howudoinb.service;

import edu.sabanciuniv.howudoinb.model.GroupModel;
import edu.sabanciuniv.howudoinb.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public GroupModel getGroupById(int id) {
	return groupRepository.findById(id).orElse(null);
    }

    public GroupModel saveGroup(GroupModel group) {
	return groupRepository.save(group);
    }

    public void deleteGroup(int id) {
	groupRepository.deleteById(id);
    }
}
