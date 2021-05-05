package com.example.config;

import com.example.db.UserEntity;
import com.example.db.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class LoadDatabase {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initTables() {
        System.out.println("================================================== Creating Sample Data ==================================================");
        userRepository.save(new UserEntity("John Smith"));
        userRepository.save(new UserEntity("Dane Smith"));
        System.out.println("================================================== Sample Data Created ==================================================");
    }

}
