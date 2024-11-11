package edu.sabanciuniv.howudoinb.controller;

import edu.sabanciuniv.howudoinb.model.FriendModel;
import edu.sabanciuniv.howudoinb.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FriendController {

    @Autowired
    private FriendService friendService;

    @GetMapping("/friend/{id}")
    public FriendModel getFriendById(@PathVariable int id) {
	return friendService.getFriendById(id);
    }

    @PostMapping("/friend")
    public FriendModel saveFriend(@RequestBody FriendModel friend) {
	return friendService.saveFriend(friend);
    }

    @DeleteMapping("/friend/{id}")
    public void deleteFriend(@PathVariable int id) {
	friendService.deleteFriend(id);
    }
}
