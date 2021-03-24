package com.example.controller;

import com.example.db.entities.UserEntity;
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
    public UserEntity newUser(@RequestBody UserEntity userEntity) {
        UserEntity newUser = userService.addUser(userEntity);
        return newUser;
    }

    @GetMapping("/users/{id}")
    public UserEntity getUser(@PathVariable long id) {
        UserEntity user = userService.getUserById(id);

        if (user == null) {
            logger.warn("User with the id={} not found", id);
            throw new NotFoundException("User with the id=" + id + " not found");
        }
        return user;
    }
}
