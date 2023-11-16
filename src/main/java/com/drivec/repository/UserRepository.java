package com.drivec.repository;

import com.drivec.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    //leave blank for now.
}
