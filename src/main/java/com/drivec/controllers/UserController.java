package com.drivec.controllers;

import com.drivec.models.Photo;
import com.drivec.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.drivec.models.User;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    //add user
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", methods = {RequestMethod.POST})
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestParam String username, Model model) {
        User user = new User(username);
        if(userService.getUser(username) == null) {
            return userService.addUser(user);

        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", methods = {RequestMethod.GET})
    @GetMapping("/{username}")
    public User getPhoto(@PathVariable String username) {

        return userService.getUser(username);

    }

}