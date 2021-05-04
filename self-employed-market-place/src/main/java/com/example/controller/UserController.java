package com.example.controller;

import com.example.db.UserEntity;
import com.example.exception.NotFoundException;
import com.example.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserService userService;

    @PostMapping("/users")
    public UserEntity createUser(@RequestBody UserEntity userEntity) {
        UserEntity newUser = userService.addUser(userEntity);
        return newUser;
    }

    @GetMapping("/users/{id}")
    public UserEntity getUser(@PathVariable long id) {
        UserEntity user = userService.getUserById(id);

        if (user == null) {
            logger.warn("User not found, id={}", id);
            throw new NotFoundException("User not found, id=" + id);
        }
        return user;
    }
}
