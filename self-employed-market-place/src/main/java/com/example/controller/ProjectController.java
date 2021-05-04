package com.example.controller;

import com.example.db.ProjectEntity;
import com.example.services.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public ProjectService projectService;

    @PostMapping("/projects")
    public ProjectEntity newUser(@RequestBody ProjectEntity projectEntity) {
        ProjectEntity newProject = projectService.addProject(projectEntity);
        return newProject;
    }

}
