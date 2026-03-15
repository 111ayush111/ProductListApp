package com.bitsandbytes.product.controller;

import com.bitsandbytes.product.entity.User;
import com.bitsandbytes.product.repository.UserRepository;
import com.bitsandbytes.product.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public MyUserDetailsService myUserDetailsService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return myUserDetailsService.createUser(user);
    }
}
