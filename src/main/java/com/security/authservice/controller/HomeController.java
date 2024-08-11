package com.security.authservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome(){
        return "This is a home page";
    }

}
