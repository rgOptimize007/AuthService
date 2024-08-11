package com.security.authservice.controller;

import com.security.authservice.dto.UserDto;
import com.security.authservice.entities.User;
import com.security.authservice.service.AuthService;
import com.security.authservice.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDto userDto){
        User user = customUserDetailService.registUser(userDto);
        if(user != null) return user.getPassword();
        else return "";
    }

    @PostMapping("/validate/{token}")
    public ResponseEntity<Boolean> validateToken(@PathVariable String token){

        //validation process here
        Boolean isValid = authService.validateToken(token);
        return ResponseEntity.ok(isValid);
    }

    @GetMapping("/hello")
    public String getHello(){
        return "Hello User";
    }
}
