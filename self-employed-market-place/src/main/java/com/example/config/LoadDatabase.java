package com.example.config;

import com.example.db.BidEntity;
import com.example.db.ProjectEntity;
import com.example.db.UserEntity;
import com.example.services.BidService;
import com.example.services.ProjectService;
import com.example.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Calendar;

@Configuration
public class LoadDatabase {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private BidService bidService;

    private static Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @PostConstruct
    public void initTables() {
        logger.info("================================================== Creating Sample Data ==================================================");

        UserEntity user1 = userService.addUser(new UserEntity("John Smith"));
        UserEntity user2 = userService.addUser(new UserEntity("Dane Smith"));
        UserEntity user3 = userService.addUser(new UserEntity("Steve Smith"));
        UserEntity user4 = userService.addUser(new UserEntity("Will Smith"));

        ProjectEntity project1 = projectService.addProject(createProjectEntity("Home Renovation", "Renovation plan for entire house", user1));
        ProjectEntity project2 = projectService.addProject(createProjectEntity("Bathroom Renovation", "Renovation plan for Master Bathroom & Hallway Bathroom", user1));
        ProjectEntity project3 = projectService.addProject(createProjectEntity("Backyard Renovation", "Renovation plan for Backyard", user2));

        bidService.addBid(project1.getId(), createBidEntity(user3, 100d));
        bidService.addBid(project1.getId(), createBidEntity(user4, 50d));

        bidService.addBid(project2.getId(), createBidEntity(user1, 500d));
        bidService.addBid(project2.getId(), createBidEntity(user2, 50d));
        bidService.addBid(project2.getId(), createBidEntity(user3, 250d));
        bidService.addBid(project2.getId(), createBidEntity(user4, 300d));

        bidService.addBid(project3.getId(), createBidEntity(user3, 50d));
        bidService.addBid(project3.getId(), createBidEntity(user4, 100d));

        logger.info("================================================== Sample Data Created ==================================================");
    }

    private ProjectEntity createProjectEntity(String name, String details, UserEntity creator) {

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName(name);
        projectEntity.setDetails(details);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        projectEntity.setBidEnd(cal.getTime());

        projectEntity.setCreatorId(creator.getId());
        return projectEntity;
    }

    private BidEntity createBidEntity(UserEntity bidder, double price) {
        BidEntity bidEntity = new BidEntity();
        bidEntity.setBidderId(bidder.getId());
        bidEntity.setPrice(price);
        return bidEntity;
    }

}
