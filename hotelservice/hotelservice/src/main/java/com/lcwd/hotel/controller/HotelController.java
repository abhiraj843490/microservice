package com.lcwd.hotel.controller;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/hotel")
@RestController
public class HotelController {
    @Autowired
    private HotelService service;
    @PostMapping
    public ResponseEntity<Hotel>createHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = service.createHotel(hotel);
        return ResponseEntity.ok().body(hotel1);
    }
    @GetMapping
    public List<Hotel> getAll(){
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Optional<Hotel> getHotel(@PathVariable("id") String id){
        return service.getHotel(id);
    }
}
