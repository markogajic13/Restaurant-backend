package com.restaurant.restaurant.service.Impl;

import com.restaurant.restaurant.config.JwtProvider;
import com.restaurant.restaurant.model.User;
import com.restaurant.restaurant.repository.UserRepository;
import com.restaurant.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        User user = findUserByEmail(jwtProvider.getEmailFromJwtToken(jwt));
        /*
         String email = jwtProvider.getEmailFromJwtToken(jwt);
         User user = findUserByEmail(email);
        */
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("User not found!");
        }
        return user;
    }
}
