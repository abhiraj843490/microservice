package com.lcwd.rating.repo;


import com.lcwd.rating.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RatingRepo extends JpaRepository<Rating,String> {
    List<Rating> findByUserId(String id);

    List<Rating> findByHotelId(String id);
}
