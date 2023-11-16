package com.drivec.controllers;
import com.drivec.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.drivec.models.User;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;
    //add user
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

}