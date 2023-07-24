package com.microservice.microservice.controller;

import com.microservice.microservice.entity.UserEntity;
import com.microservice.microservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@RequestMapping("/users")
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user){
        UserEntity userEntity = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userEntity);
    }
    @GetMapping
    public List<UserEntity>getAll(){
        return userService.getAllUser();
    }
    int retryCount =1;
    //implementing Retry(),CircuitBreaker()
    @GetMapping("/{id}")
//    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<UserEntity>getUser(@PathVariable String id){
        retryCount++;
        log.info("retry count: {}",retryCount);
        UserEntity user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }


    //creating fall back method for circuit breaker
    public ResponseEntity<UserEntity> ratingHotelFallback(String id, Exception e){
        log.info("fallback is executed because service is down: ",e.getMessage());
        UserEntity user = UserEntity.builder()
                .email("dummy@gamil.com")
                .name("dummy")
                .about("dummy because services is down")
                .userId("2343").build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
