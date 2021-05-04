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
        //TODO: validate the creatorId, throw 400 if not found
        return projectRepository.save(projectEntity);
    }

    public ProjectEntity getProject(Long id) {
        return projectRepository.getById(id);
    }

}
