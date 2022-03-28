package com.example.tmpelectronics.controller;


import com.example.tmpelectronics.entity.User;
import com.example.tmpelectronics.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute User user) {
        userService.save(user);
        return "index";
    }

}
