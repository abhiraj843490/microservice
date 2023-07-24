package com.microservice.microservice.service.impl;

import com.microservice.microservice.entity.Hotel;
import com.microservice.microservice.entity.Ratings;
import com.microservice.microservice.entity.UserEntity;
import com.microservice.microservice.exception.ResourceNotFoundException;
import com.microservice.microservice.external.service.HotelService;
import com.microservice.microservice.repo.UserRepo;
import com.microservice.microservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserIml implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;
    @Override
    public UserEntity saveUser(UserEntity user) {
        String randomId = String.valueOf(UUID.randomUUID());
        user.setUserId(randomId);
        return userRepo.save(user);
    }

    @Override
    public List<UserEntity> getAllUser() {
        List<UserEntity> users = userRepo.findAll();
        for(UserEntity user: users){
            Ratings[] restTemplateForObject = restTemplate.getForObject("http://RATING-SERVICE/rate/user/"+user.getUserId(), Ratings[].class);
            List<Ratings> ratings = Arrays.stream(restTemplateForObject).toList();

            List<Ratings> rate = ratings.stream().map(rating -> {
                Hotel hotel = restTemplate.getForObject("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());


            user.setRating(rate);
        }
        return users;
    }

    @Override
    public UserEntity getUser(String id) {
        UserEntity user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("user of given id is not "+id));
        Ratings[] restTemplateForObject = restTemplate.getForObject("http://RATING-SERVICE/rate/user/"+user.getUserId(), Ratings[].class);

        List<Ratings>ratings = Arrays.stream(restTemplateForObject).toList();

        List<Ratings>ratingsList = ratings.stream().map(rating -> {
//            Hotel forObject = restTemplate.getForObject("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
            Hotel forObject = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(forObject);
            return rating;
        }).collect(Collectors.toList());

        user.setRating(ratingsList);
        return user;
    }
}
