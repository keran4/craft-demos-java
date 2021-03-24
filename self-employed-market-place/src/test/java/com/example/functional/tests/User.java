package com.example.functional.tests;

import com.example.db.entities.UserEntity;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class User {

    private static final String endpoint = "http://localhost:8080/users";

    @Test
    public void addUserTest() {
        UserEntity userEntity = new UserEntity("Donald Duck");

        Response resp = RestAssured.given()
                .contentType("application/json")
                .body(userEntity)
                .post(endpoint);

        Assert.assertEquals(resp.statusCode(), 200);
        System.out.println(resp.getBody().asString());

    }
}
