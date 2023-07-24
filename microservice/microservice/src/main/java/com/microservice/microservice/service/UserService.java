package com.microservice.microservice.service;


import com.microservice.microservice.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity saveUser(UserEntity user);
    List<UserEntity>getAllUser();
    UserEntity getUser(String id);
}
