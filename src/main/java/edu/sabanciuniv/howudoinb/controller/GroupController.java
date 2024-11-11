package edu.sabanciuniv.howudoinb.controller;

import edu.sabanciuniv.howudoinb.model.GroupModel;
import edu.sabanciuniv.howudoinb.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GroupController {

	@Autowired
	private GroupService groupService;

	@PostMapping("/groups/create")
	public GroupModel createGroup(@RequestBody GroupModel group) {
		return groupService.saveGroup(group);
	}

	@PostMapping("/groups/{groupId}/add-member")
	public GroupModel addMember(@PathVariable int groupId, @RequestBody GroupModel group) {
		return groupService.saveGroup(group);
	}

	@PostMapping("/groups/{groupId}/send")
	public GroupModel sendMessage(@PathVariable int groupId, @RequestBody GroupModel group) {
		return groupService.saveGroup(group);
	}

	@GetMapping("/groups/{groupId}/messages")
	public GroupModel getMessages(@PathVariable int groupId) {
		return groupService.getGroupById(groupId);
	}

	@GetMapping("/groups/{groupId}/members")
	public GroupModel getMembers(@PathVariable int groupId) {
		return groupService.getGroupById(groupId);
	}
}
