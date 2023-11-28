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

    public User addUser(User user) {

        return userRepo.insert(user);
    }

    public User getUser(String username) {
        List<User> users = userRepo.findByUserName(username);

        if (!users.isEmpty()) {
            return users.get(0);
        } else {
            return null;
        }
    }

}
