package com.example.functional.tests;

import com.example.db.BidEntity;
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

public class BidTest extends TestBase {

    @Value("${resource.bids}")
    private String bidsResource;

    @Autowired
    private TestDataCreateHelper testDataCreateHelper;

    @Test
    public void addBidTest() {

        UserEntity seller = testDataCreateHelper.createUser();
        ProjectEntity projectEntity = testDataCreateHelper.createProject(seller);
        UserEntity bidder = testDataCreateHelper.createUser();

        final String endpoint = this.host + bidsResource.replace("{projectId}", projectEntity.getId().toString());

        BidEntity bid = testDataCreateHelper.createBidEntity(bidder, 100D);

        Response resp = RestAssured.given()
                .contentType("application/json")
                .body(bid)
                .post(endpoint);

        Assert.assertEquals(resp.statusCode(), 200);

        Assert.assertNotNull(resp.jsonPath().getString("id"));
        Assert.assertEquals(resp.jsonPath().getString("projectId"), projectEntity.getId().toString());
        Assert.assertEquals(resp.jsonPath().getString("bidderId"), bidder.getId().toString());
        Assert.assertEquals(resp.jsonPath().getString("price"), bid.getPrice().toString());
        Assert.assertNotNull(resp.jsonPath().getString("createdOn"));
    }

    @Test
    public void getBidTest() {

        UserEntity seller = testDataCreateHelper.createUser();
        ProjectEntity projectEntity = testDataCreateHelper.createProject(seller);
        UserEntity bidder = testDataCreateHelper.createUser();

        final String endpoint = this.host + bidsResource.replace("{projectId}", projectEntity.getId().toString());

        BidEntity bid1 = testDataCreateHelper.createBid(projectEntity, bidder, 100D);
        BidEntity bid2 = testDataCreateHelper.createBid(projectEntity, bidder, 50D);

        Response resp = RestAssured.given()
                .contentType("application/json")
                .get(endpoint);

        Assert.assertEquals(resp.statusCode(), 200);

        Assert.assertEquals(resp.jsonPath().getString("id[0]"), bid1.getId().toString());
        Assert.assertEquals(resp.jsonPath().getString("price[0]"), bid1.getPrice().toString());

        Assert.assertEquals(resp.jsonPath().getString("id[1]"), bid2.getId().toString());
        Assert.assertEquals(resp.jsonPath().getString("price[1]"), bid2.getPrice().toString());
    }

}
