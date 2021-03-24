package com.example.services;

import com.example.db.entities.UserEntity;
import com.example.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public UserEntity addUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
