package com.drivec.services;

import com.drivec.models.User;
import com.drivec.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@TestPropertySource(locations = "classpath:/application.properties")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddUser() {
        // 1. Successfully add one user
        User user1 = new User("username1");
        User addedUser1 = userService.addUser(user1);
        assertNotNull(addedUser1);
        assertEquals(user1.getUserName(), addedUser1.getUserName());

        // 2. Add another user with the same username, should return null
        User userWithDuplicateUsername = new User("username1");
        User addedUser2 = userService.addUser(userWithDuplicateUsername);
        assertNull(addedUser2);
    }
}
