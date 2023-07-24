package com.lcwd.rating.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    private String ratingId;
    private String hotelId;
    private String userId;
    private Integer rating;
    private String feedback;

}
