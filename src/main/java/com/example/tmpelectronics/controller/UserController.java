package com.example.tmpelectronics.controller;


import com.example.tmpelectronics.dto.UserCredRequestDto;
import com.example.tmpelectronics.dto.UserRequestDto;
import com.example.tmpelectronics.entity.User;
import com.example.tmpelectronics.security.CurrentUser;
import com.example.tmpelectronics.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute UserCredRequestDto userRequestDto, @RequestParam("confirmPassword") String confirmPassword, ModelMap map) {
        User user = mapper.map(userRequestDto, User.class);
        if(!user.getPassword().equals(confirmPassword)) {
            map.addAttribute("errorMsgs", "Passwords doesnt match"); // sirunacnel errori html
            return "account";
        }
        userService.create(user);
        return "redirect:/";
    }


    @PostMapping("/editUser")
    public String editUser(@ModelAttribute UserRequestDto userRequestDto, @AuthenticationPrincipal CurrentUser currentUser) {
        if(!userRequestDto.getName().equals("")) {
            currentUser.getUser().setName(userRequestDto.getName());
        }
        if(!userRequestDto.getSurname().equals("")) {
            currentUser.getUser().setSurname(userRequestDto.getSurname());
        }
        if(!userRequestDto.getPhone().equals("")) {
            currentUser.getUser().setPhone(userRequestDto.getPhone());
        }
        if(!userRequestDto.getAddress().equals("")) {
            currentUser.getUser().setAddress(userRequestDto.getAddress());
        }
        userService.save(currentUser.getUser());
        return "redirect:/myAccount";
    }



}
