package com.lcwd.hotel.service;

import com.lcwd.hotel.entities.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAll();

    Optional<Hotel> getHotel(String id);
}
