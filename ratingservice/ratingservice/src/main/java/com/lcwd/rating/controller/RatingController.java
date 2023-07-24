package com.lcwd.rating.controller;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/rate")
@RestController
public class RatingController {
    @Autowired
    RatingService service;
    @PostMapping
    public ResponseEntity<Rating> createRate(@RequestBody Rating rating){
        Rating rating1 = service.create(rating);
                return ResponseEntity.ok().body(rating1);
    }
    @GetMapping
    public List<Rating> getAll(){
        return service.getAll();
    }
    @GetMapping("/user/{userId}")
    public List<Rating> getAllByUserId(@PathVariable("userId") String id){
        return service.getAllByUserId(id);
    }
    @GetMapping("/hotel/{hotelId}")
    public List<Rating> getAllByHotelId(@PathVariable("hotelId") String id){
        return service.getAllByHotelId(id);
    }
}
