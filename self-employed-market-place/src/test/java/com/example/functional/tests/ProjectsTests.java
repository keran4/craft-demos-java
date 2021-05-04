package com.example.functional.tests;

import com.example.db.ProjectEntity;
import com.example.db.UserEntity;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Calendar;

public class ProjectsTests extends TestBase{

    @Value("${resource.projects}")
    private String projectsResource;

    @Value("${resource.users}")
    private String usersResource;

    @Test
    public void addProjectsTest() {

        final String endpoint = this.host + projectsResource;

        UserEntity newUser = createUser();
        ProjectEntity projectEntity = createProjectEntity(newUser);

        Response resp = RestAssured.given()
                .contentType("application/json")
                .body(projectEntity)
                .post(endpoint);

        Assert.assertEquals(resp.statusCode(), 200);

        Assert.assertEquals(resp.jsonPath().getString("name"), projectEntity.getName());
        Assert.assertEquals(resp.jsonPath().getString("details"), projectEntity.getDetails());
        Assert.assertEquals(resp.jsonPath().getString("creatorId"), newUser.getId().toString());
        Assert.assertNotNull(resp.jsonPath().getString("id"));
    }

    @Test
    public void getProjectTest() {

        final String endpoint = this.host + projectsResource;

        ProjectEntity projectEntity = createProjectEntity(createUser());

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

    private UserEntity createUser() {

        UserEntity userEntity = new UserEntity("Donald Duck");

        Response resp = RestAssured.given()
                .contentType("application/json")
                .body(userEntity)
                .post(this.host + usersResource);

        Assert.assertEquals(resp.statusCode(), 200);

        return resp.body().as(UserEntity.class);
    }

    private ProjectEntity createProjectEntity(UserEntity user) {

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName("Donald Duck's 1st Project");
        projectEntity.setDetails("This is a test project by Donald Duck");

        projectEntity.setCreatorId(user.getId());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        projectEntity.setBidEnd(cal.getTime());

        return projectEntity;
    }
}
