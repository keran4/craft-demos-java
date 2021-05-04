package com.example.services;

import com.example.db.ProjectEntity;
import com.example.db.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    public ProjectRepository projectRepository;

    public ProjectEntity addProject(ProjectEntity projectEntity) {
        return projectRepository.save(projectEntity);
    }

}
