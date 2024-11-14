package edu.sabanciuniv.howudoinb.controller;

import edu.sabanciuniv.howudoinb.model.GroupModel;
import edu.sabanciuniv.howudoinb.model.UserModel;
import edu.sabanciuniv.howudoinb.repository.UserRepository;
import edu.sabanciuniv.howudoinb.service.GroupService;
import edu.sabanciuniv.howudoinb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GroupController {

	@Autowired
	private GroupService groupService;

	@Autowired
	private UserService userService;

	@PostMapping("/groups/create")
	public int createGroup(@RequestHeader("Authorization") String token, @RequestBody GroupModel group) {

		//get creator from token
		String actualToken = token.startsWith("Bearer ") ? token.substring(7) : null;
		String email =  userService.whoSent(actualToken);

		//validate group
		if (group.validateCreate() != 0) {
			return -1;
		}
		System.out.println("got here");

		//pass to service(returns -1, or groupid)
		return groupService.createGroup(group, email);
	}

	@PostMapping("/groups/{groupId}/add-member")
	public int addMember(@RequestHeader("Authorization") String token, @PathVariable int groupId, @RequestBody UserModel newMember) {
		//get request-er from token
		String actualToken = token.startsWith("Bearer ") ? token.substring(7) : null;
		String email =  userService.whoSent(actualToken);

		//verify user is valid (at least email)
		if (newMember.senderValidate() != 0) {
			return -1;
		}

		//pass to service
		return groupService.addMember(groupId, newMember, email);
	}
//
//	@PostMapping("/groups/{groupId}/send")
//	public GroupModel sendMessage(@PathVariable int groupId, @RequestBody GroupModel group) {
//		return groupService.saveGroup(group);
//	}
//
//	@GetMapping("/groups/{groupId}/messages")
//	public GroupModel getMessages(@PathVariable int groupId) {
//		return groupService.getGroupById(groupId);
//	}
//
//	@GetMapping("/groups/{groupId}/members")
//	public GroupModel getMembers(@PathVariable int groupId) {
//		return groupService.getGroupById(groupId);
//	}
}
