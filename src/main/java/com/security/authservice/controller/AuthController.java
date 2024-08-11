package com.security.authservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {


    @GetMapping("/hello")
    public String validateUser(){
        return "Hello User!!";
    }

}
