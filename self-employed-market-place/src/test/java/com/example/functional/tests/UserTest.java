package com.example.functional.tests;

import com.example.db.UserEntity;
import com.example.functional.TestBase;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTest extends TestBase {

    @Value("${resource.users}")
    private String usersResource;

    @Test(invocationCount=5)
    public void addUserTest() {

        final String endpoint = this.host + usersResource;

        UserEntity userEntity = new UserEntity("Donald Duck");

        Response resp = RestAssured.given()
                .contentType("application/json")
                .body(userEntity)
                .post(endpoint);

        Assert.assertEquals(resp.statusCode(), 200);

        userEntity = resp.getBody().as(UserEntity.class);

        resp = RestAssured.given()
                .contentType("application/json")
                .get(endpoint + "/100");

        Assert.assertEquals(resp.statusCode(), 404);
        Assert.assertEquals(resp.getBody().asString(), "User not found, id=100");

        resp = RestAssured.given()
                .contentType("application/json")
                .get(endpoint + "/" + userEntity.getId());

        Assert.assertEquals(resp.statusCode(), 200);
        Assert.assertEquals(resp.jsonPath().getString("name"), userEntity.getName());
    }
}
