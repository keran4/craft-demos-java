package com.example.config;

import com.example.db.entities.UserEntity;
import com.example.db.repository.UserRepository;
import com.example.utils.SerializationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class LoadDatabase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SerializationUtil serializationUtil;

    @PostConstruct
    public void initTables() {
        userRepository.save(new UserEntity("John Smith"));
        userRepository.save(new UserEntity("Dane Smith"));
    }

}
