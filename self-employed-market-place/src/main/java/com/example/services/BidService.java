package com.example.services;

import com.example.db.BidEntity;
import com.example.db.BidRepository;
import com.example.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BidService {

    @Autowired
    public BidRepository bidRepository;

    public BidEntity addBid(Long projectId, BidEntity bid) {
        bid.setProjectId(projectId);
        bid.setCreatedOn(new Date());

        //TODO: Validate projectId & bidderId. Throw 400 if found invalid
        //TODO: Validate to ensure the Bid Time has not passed, throw 400 if so

        if (bid.getPrice() < 0) {
            throw new BadRequestException("Price is below Zero");
        }

        return bidRepository.save(bid);
    }

    public List<BidEntity> getBids(Long projectId) {
        return bidRepository.getByProjectId(projectId);
    }
}
