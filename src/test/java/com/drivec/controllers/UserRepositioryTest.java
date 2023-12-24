package com.drivec.controllers;

import com.drivec.models.User;
import com.drivec.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserRepositioryTest {
    @Autowired
    private UserRepository userRepoTest;

    @Test
    void itShouldCheckIfUserExistsUsername() {
        //given
        User user = new User("test");
        userRepoTest.insert(user);

        //when
        boolean expected = userRepoTest.findByUserName("test") != null;

        //then
        assertThat(expected).isTrue();
    }
}
