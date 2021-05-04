package com.example.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<BidEntity, Long> {

    public BidEntity getById(long id);

    public List<BidEntity> getByProjectId(long projectId);
}
