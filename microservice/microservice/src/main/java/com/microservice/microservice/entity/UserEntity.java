package com.microservice.microservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_user")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "ID")
    private String userId;
    private String name;
    private String email;
    private String about;
    @Transient
    private List<Ratings> rating= new ArrayList<>();
}
