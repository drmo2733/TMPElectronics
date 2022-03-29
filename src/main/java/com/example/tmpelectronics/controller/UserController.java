package com.example.tmpelectronics.controller;


import com.example.tmpelectronics.entity.User;
import com.example.tmpelectronics.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute User user, @RequestParam("confirmPassword") String confirmPassword, ModelMap map) {
        if(!user.getPassword().equals(confirmPassword)) {
            map.addAttribute("errorMsgs", "Passwords doesnt match"); // sirunacnel errori html
            return "account";
        }
        userService.save(user);
        return "redirect:/";
    }

}
