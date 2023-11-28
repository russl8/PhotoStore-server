package com.drivec.repository;

import com.drivec.models.Photo;
import com.drivec.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    @Query("{userName: ?0}")
    User getUser(String userName);

    List<User> findByUserName(String username);
}
