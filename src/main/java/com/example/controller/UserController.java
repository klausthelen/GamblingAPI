package com.example.controller;


import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User login(@RequestBody Map<String, Object> payload) {
        User user = userService.login((String) payload.get("username"), (String)payload.get("password"));
        return user;
    }
}