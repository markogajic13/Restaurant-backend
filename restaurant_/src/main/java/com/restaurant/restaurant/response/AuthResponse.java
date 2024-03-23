package com.restaurant.restaurant.response;

import com.restaurant.restaurant.model.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
