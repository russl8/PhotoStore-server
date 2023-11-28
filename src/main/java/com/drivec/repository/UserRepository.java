package com.drivec.repository;

import com.drivec.models.Photo;
import com.drivec.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    /**
     *
     * @param username searches db for user with specified username.
     * @return user object if user is found. else, null.
     */
    User findByUserName(String username);
}
