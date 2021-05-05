package com.example.functional.tests;

import com.example.db.ProjectEntity;
import com.example.functional.TestBase;
import com.example.functional.helper.TestDataCreateHelper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectsPaginationTest extends TestBase {

    @Value("${resource.projects}")
    private String projectsResource;

    @Autowired
    private TestDataCreateHelper testDataCreateHelper;

    @Test
    public void latest5RecordsTest() {

        testDataCreateHelper.createProject(testDataCreateHelper.createUser());
        testDataCreateHelper.createProject(testDataCreateHelper.createUser());
        testDataCreateHelper.createProject(testDataCreateHelper.createUser());
        testDataCreateHelper.createProject(testDataCreateHelper.createUser());
        testDataCreateHelper.createProject(testDataCreateHelper.createUser());
        testDataCreateHelper.createProject(testDataCreateHelper.createUser());
        testDataCreateHelper.createProject(testDataCreateHelper.createUser());
        ProjectEntity projectEntity = testDataCreateHelper.createProject(testDataCreateHelper.createUser());

        final String endpoint = this.host + projectsResource;

        Response resp = RestAssured.given()
                .contentType("application/json")
                .get(endpoint + "?page_num=0&page_size=5");

        Assert.assertEquals(resp.statusCode(), 200);

        Assert.assertEquals(resp.jsonPath().getList("").size(), 5);
        Assert.assertEquals(resp.jsonPath().getString("id[0]"), projectEntity.getId().toString());
    }
}
