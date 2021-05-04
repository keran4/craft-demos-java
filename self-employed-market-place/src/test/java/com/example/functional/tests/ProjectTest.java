package com.example.functional.tests;

import com.example.db.ProjectEntity;
import com.example.db.UserEntity;
import com.example.functional.TestBase;
import com.example.functional.helper.TestDataCreateHelper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Calendar;

public class ProjectTest extends TestBase {

    @Value("${resource.projects}")
    private String projectsResource;

    @Autowired
    private TestDataCreateHelper testDataCreateHelper;

    @Test
    public void addProjectsTest() {

        final String endpoint = this.host + projectsResource;

        UserEntity seller = testDataCreateHelper.createUser();
        ProjectEntity projectEntity = testDataCreateHelper.createProjectEntity(seller);

        Response resp = RestAssured.given()
                .contentType("application/json")
                .body(projectEntity)
                .post(endpoint);

        Assert.assertEquals(resp.statusCode(), 200);

        Assert.assertEquals(resp.jsonPath().getString("name"), projectEntity.getName());
        Assert.assertEquals(resp.jsonPath().getString("details"), projectEntity.getDetails());
        Assert.assertEquals(resp.jsonPath().getString("creatorId"), seller.getId().toString());
        Assert.assertNotNull(resp.jsonPath().getString("id"));
    }

    @Test
    public void getProjectTest() {

        final String endpoint = this.host + projectsResource;

        ProjectEntity projectEntity = testDataCreateHelper.createProjectEntity(testDataCreateHelper.createUser());

        Response resp = RestAssured.given()
                .contentType("application/json")
                .body(projectEntity)
                .post(endpoint);

        Assert.assertEquals(resp.statusCode(), 200);

        projectEntity = resp.getBody().as(ProjectEntity.class);

        final String getEndpoint = endpoint + "/" + projectEntity.getId();
        resp = RestAssured.given()
                .contentType("application/json")
                .get(getEndpoint);

        Assert.assertEquals(resp.statusCode(), 200);

        Assert.assertEquals(resp.jsonPath().getString("id"), projectEntity.getId().toString());
        Assert.assertEquals(resp.jsonPath().getString("name"), projectEntity.getName());

        resp = RestAssured.given()
                .contentType("application/json")
                .get(endpoint + "/" + projectEntity.getId()+10000);

        Assert.assertEquals(resp.statusCode(), 404);
    }

}
