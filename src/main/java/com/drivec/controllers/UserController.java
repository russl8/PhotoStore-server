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

    /**
     *
     * @param username name of user signing up.
     * @param model of the user
     * @return a user object if a unique username is created. otherwise, return null.
     */
    @CrossOrigin(origins = "main--guileless-cheesecake-e176d5.netlify.app", allowCredentials = "true", methods = {RequestMethod.POST})
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestParam  String username, Model model) {
        System.out.println("creating user " + username);
        User user = new User(username);
        if (userService.getUser(username) == null) {
            return userService.addUser(user);
        }
        return null;
    }



    /**
     *
     * @param username username of the user that is being searched
     * @return a user object, if they exist.
     */
    @CrossOrigin(origins = "main--guileless-cheesecake-e176d5.netlify.app", allowCredentials = "true", methods = {RequestMethod.GET})
    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {

        return userService.getUser(username);
    }

}