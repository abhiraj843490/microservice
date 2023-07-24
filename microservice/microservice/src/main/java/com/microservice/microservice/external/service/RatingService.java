package com.microservice.microservice.external.service;

import com.microservice.microservice.entity.Ratings;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    @PostMapping("/rate")
    public Ratings createRating(Ratings values);

    @PutMapping("/rate/{rateId}")
    public Ratings updateRating(@PathVariable("rateId") String id, Ratings ratings);

    @DeleteMapping("/rate/{rateId}")
    public void deleteRating(@PathVariable("rateId")String id);
}
