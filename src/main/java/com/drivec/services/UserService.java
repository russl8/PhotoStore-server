package com.drivec.services;

import com.drivec.models.User;
import com.drivec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    /**
     * @param user a user object.
     * @return the user object, if the user is inserted into db.
     */
    public User addUser(User user) {
        if (userRepo.findByUserName(user.getUserName()) == null) {
            return userRepo.insert(user);
        }
        System.out.println("user already exists");
        return null;
    }

    /**
     * @param username
     * @return a user object if they are found in db. otherwise, return null
     */
    public User getUser(String username) {
        return userRepo.findByUserName(username);
    }

}
