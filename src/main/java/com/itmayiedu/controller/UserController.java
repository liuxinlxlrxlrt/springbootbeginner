package com.itmayiedu.controller;

import com.itmayiedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/createUser")
    public String createUser(String name,int age){
        return userService.createUser(name,age) ? "success":"fail";
    }
}
