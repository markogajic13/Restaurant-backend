package com.restaurant.restaurant.service;

import com.restaurant.restaurant.model.User;

public interface UserService {
    public User findUserByJwtToken(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;
}
