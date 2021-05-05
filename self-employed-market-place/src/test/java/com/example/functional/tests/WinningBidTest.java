package com.example.functional.tests;

import com.example.db.BidEntity;
import com.example.db.ProjectEntity;
import com.example.db.UserEntity;
import com.example.functional.TestBase;
import com.example.functional.helper.TestDataCreateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WinningBidTest extends TestBase {

    @Autowired
    private TestDataCreateHelper testDataCreateHelper;

    @Test
    public void happyPathTest() {

        UserEntity seller = testDataCreateHelper.createUser();
        ProjectEntity projectEntity = testDataCreateHelper.createProject(seller);
        UserEntity bidder1 = testDataCreateHelper.createUser();

        Assert.assertNull(projectEntity.getWinningBidId());

        BidEntity bid1 = testDataCreateHelper.createBid(projectEntity, bidder1, 100D);
        projectEntity = testDataCreateHelper.getProject(projectEntity.getId());
        Assert.assertEquals(projectEntity.getWinningBidId(), bid1.getId());

        UserEntity bidder2 = testDataCreateHelper.createUser();
        BidEntity bid2 = testDataCreateHelper.createBid(projectEntity, bidder2, 50D);
        projectEntity = testDataCreateHelper.getProject(projectEntity.getId());
        Assert.assertEquals(projectEntity.getWinningBidId(), bid2.getId());

        UserEntity bidder3 = testDataCreateHelper.createUser();
        BidEntity bid3 = testDataCreateHelper.createBid(projectEntity, bidder3, 75D);
        projectEntity = testDataCreateHelper.getProject(projectEntity.getId());
        Assert.assertEquals(projectEntity.getWinningBidId(), bid2.getId());

        BidEntity bid4 = testDataCreateHelper.createBid(projectEntity, bidder1, 40D);
        projectEntity = testDataCreateHelper.getProject(projectEntity.getId());
        Assert.assertEquals(projectEntity.getWinningBidId(), bid4.getId());
    }
}
