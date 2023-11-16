package com.drivec.services;

import com.drivec.models.User;
import com.drivec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public User addUser(User user) {

        return userRepo.insert(user);
    }

}
