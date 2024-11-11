package edu.sabanciuniv.howudoinb.controller;

import edu.sabanciuniv.howudoinb.model.GroupModel;
import edu.sabanciuniv.howudoinb.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GroupController {

	@Autowired
	private GroupService groupService;

	@GetMapping("/group/{id}")
	public GroupModel getGroupById(@PathVariable int id) {
	return groupService.getGroupById(id);
	}

	@PostMapping("/group")
	public GroupModel saveGroup(@RequestBody GroupModel group) {
	return groupService.saveGroup(group);
	}

	@DeleteMapping("/group/{id}")
	public void deleteGroup(@PathVariable int id) {
	groupService.deleteGroup(id);
	}
}
