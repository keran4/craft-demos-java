package com.example.controller;

import com.example.db.BidEntity;
import com.example.exception.NotFoundException;
import com.example.services.BidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BidController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    public BidService bidService;

    @PostMapping("/projects/{projectId}/bids")
    public BidEntity createBid(@PathVariable Long projectId, @RequestBody BidEntity bid) {
        BidEntity newBid = bidService.addBid(projectId, bid);
        return newBid;
    }

    @GetMapping("/projects/{projectId}/bids")
    public List<BidEntity> createBid(@PathVariable Long projectId) {
        List<BidEntity> bids = bidService.getBids(projectId);
        if (bids == null) {
            logger.warn("Bids for the project not found, id={}", projectId);
            throw new NotFoundException("Bids for the project not found, id=" + projectId);
        }
        return bids;
    }
}
