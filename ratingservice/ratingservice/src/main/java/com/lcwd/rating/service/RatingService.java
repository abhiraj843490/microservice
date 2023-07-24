package com.lcwd.rating.service;

import com.lcwd.rating.entities.Rating;

import java.util.List;


public interface RatingService {
    Rating create(Rating rating);
    List<Rating>getAll();
    List<Rating> getAllByUserId(String id);
    List<Rating>getAllByHotelId(String id);
}
