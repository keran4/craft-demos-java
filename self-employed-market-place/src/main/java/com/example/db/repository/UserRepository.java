package com.example.db.repository;

import com.example.db.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT e FROM UserEntity e WHERE e.id=?1 AND e.name=?2")
    public UserEntity getByIdAndName(long id, String name);

    public UserEntity getById(long id);

    public UserEntity getByName(long name);

}
