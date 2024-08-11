package com.security.authservice.service;

import com.security.authservice.entities.User;
import com.security.authservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    public Boolean validateToken(String token) {
        Optional<User> optionalUser = userRepository.findUserByPassword(token);
        if(optionalUser.isPresent()) return true;
        return false;
    }

    public String generateToken(String password){
        LOGGER.info("Encoding password : " + password);
        return passwordEncoder.encode(password);
    }

}
