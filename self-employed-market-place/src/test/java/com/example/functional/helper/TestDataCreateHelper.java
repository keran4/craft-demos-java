package com.example.functional.helper;

import com.example.db.BidEntity;
import com.example.db.ProjectEntity;
import com.example.db.UserEntity;
import com.example.functional.TestBase;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.testng.Assert;

import java.util.Calendar;

@Service
public class TestDataCreateHelper extends TestBase {

    @Value("${resource.projects}")
    private String projectsResource;

    @Value("${resource.users}")
    private String usersResource;

    @Value("${resource.bids}")
    private String bidsResource;

    @Value("${host}")
    protected String host;

    public UserEntity createUser() {

        UserEntity userEntity = new UserEntity("Donald Duck");

        Response resp = RestAssured.given()
                .contentType("application/json")
                .body(userEntity)
                .post(this.host + usersResource);

        Assert.assertEquals(resp.statusCode(), 200);

        return resp.body().as(UserEntity.class);
    }

    public ProjectEntity createProjectEntity(UserEntity user) {

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName("Donald Duck's 1st Project");
        projectEntity.setDetails("This is a test project by Donald Duck");

        projectEntity.setCreatorId(user.getId());

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        projectEntity.setBidEnd(cal.getTime());

        return projectEntity;
    }

    public ProjectEntity createProject(UserEntity user) {

        ProjectEntity projectEntity = createProjectEntity(user);

        Response resp = RestAssured.given()
                .contentType("application/json")
                .body(projectEntity)
                .post(this.host + projectsResource);

        Assert.assertEquals(resp.statusCode(), 200);

        return resp.getBody().as(ProjectEntity.class);
    }

    public BidEntity createBidEntity(UserEntity bidder, Double price) {
        BidEntity bidEntity = new BidEntity();
        bidEntity.setBidderId(bidder.getId());
        bidEntity.setPrice(price);
        return bidEntity;
    }

    public BidEntity createBid(ProjectEntity projectEntity, UserEntity bidder, Double price) {
        BidEntity bid = createBidEntity(bidder, price);

        final String endpoint = this.host + bidsResource.replace("{projectId}", projectEntity.getId().toString());

        Response resp = RestAssured.given()
                .contentType("application/json")
                .body(bid)
                .post(endpoint);

        Assert.assertEquals(resp.statusCode(), 200);
        return resp.getBody().as(BidEntity.class);
    }
}
