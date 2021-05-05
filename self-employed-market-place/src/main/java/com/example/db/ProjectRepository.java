package com.example.db;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<ProjectEntity, Long> {

    public ProjectEntity getById(long id);
}
