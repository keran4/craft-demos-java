package com.example.db;

import javax.persistence.*;

@Table(name="user")
@Entity
public class UserEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public UserEntity() {}

    public UserEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
