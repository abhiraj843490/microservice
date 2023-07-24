package com.lcwd.hotel.service.impl;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.repo.HotelRepo;
import com.lcwd.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HotelImpl implements HotelService {
    @Autowired
    HotelRepo repo;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomId = UUID.randomUUID().toString();
        hotel.setId(randomId);
        return repo.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Hotel> getHotel(String id) {
        return repo.findById(id);
    }
}
