package com.lcwd.rating.service.impl;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repo.RatingRepo;
import com.lcwd.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RatingImpl implements RatingService {
    @Autowired
    private RatingRepo repo;
    @Override
    public Rating create(Rating rating) {
        String randomId = UUID.randomUUID().toString();
        rating.setRatingId(randomId);
        return repo.save(rating);
    }

    @Override
    public List<Rating> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Rating> getAllByUserId(String id) {
        System.out.println(repo.findByUserId(id));
        return repo.findByUserId(id);
    }

    @Override
    public List<Rating> getAllByHotelId(String id) {
        return repo.findByHotelId(id);
    }
}
