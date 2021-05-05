package com.example.services;

import com.example.db.BidEntity;
import com.example.db.BidRepository;
import com.example.db.ProjectEntity;
import com.example.db.ProjectRepository;
import com.example.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class BidService {

    @Autowired
    public BidRepository bidRepository;

    @Autowired
    public ProjectRepository projectRepository;

    @Transactional
    public BidEntity addBid(Long projectId, BidEntity bid) {
        bid.setProjectId(projectId);
        bid.setCreatedOn(new Date());

        //TODO: Validate projectId & bidderId. Throw 400 if found invalid
        //TODO: Validate to ensure the Bid Time has not passed, throw 400 if so

        if (bid.getPrice() == null ||  bid.getPrice() < 0)
            throw new BadRequestException("Invalid Price");

        BidEntity newBid = bidRepository.save(bid);
        ProjectEntity projectEntity = projectRepository.getById(newBid.getProjectId());
        BidEntity winningBid = projectEntity.getWinningBidId() == null ? null : bidRepository.getById(projectEntity.getWinningBidId());

        if (winningBid == null || winningBid.getPrice() > newBid.getPrice()){
            projectEntity.setWinningBidId(newBid.getId());
            projectRepository.save(projectEntity);
        }

        return newBid;
    }

    public List<BidEntity> getBids(Long projectId) {
        return bidRepository.getByProjectId(projectId);
    }
}
