package com.example.services;

import com.example.db.ProjectEntity;
import com.example.db.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    public ProjectRepository projectRepository;

    public ProjectEntity addProject(ProjectEntity projectEntity) {
        //TODO: validate the creatorId, throw 400 if not found
        projectEntity.setCreatedOn(new Date());
        return projectRepository.save(projectEntity);
    }

    public ProjectEntity getProject(Long id) {
        return projectRepository.getById(id);
    }

    public List<ProjectEntity> getLatestProjects(int pageNum, int pageSize) {
        Pageable pageableProjects = PageRequest.of(pageNum, pageSize, Sort.by("createdOn").descending());
        Page<ProjectEntity> result = projectRepository.findAll(pageableProjects);
        return result.getContent();
    }

}
