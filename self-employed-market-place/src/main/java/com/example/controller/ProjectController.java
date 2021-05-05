package com.example.controller;

import com.example.db.ProjectEntity;
import com.example.exception.NotFoundException;
import com.example.services.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    public ProjectService projectService;

    @PostMapping("/projects")
    public ProjectEntity createProject(@RequestBody ProjectEntity projectEntity) {
        ProjectEntity newProject = projectService.addProject(projectEntity);
        return newProject;
    }

    @GetMapping("/projects")
    public List<ProjectEntity> getProjects(@RequestParam (defaultValue = "0") int page_num, @RequestParam (defaultValue = "5") int page_size) {
        return projectService.getLatestProjects(page_num,page_size);
    }

    @GetMapping("/projects/{id}")
    public ProjectEntity getProject(@PathVariable Long id) {
        ProjectEntity project = projectService.getProject(id);
        if (project == null) {
            logger.warn("Project not found, id={}", id);
            throw new NotFoundException("Project not found, id=" + id);
        }
        return project;
    }
}
