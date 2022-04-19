package com.example.tmpelectronics.controller;


import com.example.tmpelectronics.dto.UserCredRequestDto;
import com.example.tmpelectronics.dto.UserRequestDto;
import com.example.tmpelectronics.entity.User;
import com.example.tmpelectronics.security.CurrentUser;
import com.example.tmpelectronics.service.MailService;
import com.example.tmpelectronics.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MailService mailService;
    private final ModelMapper mapper;

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute @Valid UserCredRequestDto userRequestDto, BindingResult bindingResult, @RequestParam("confirmPassword") String confirmPassword, ModelMap map, Locale locale) throws MessagingException {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (ObjectError allError : bindingResult.getAllErrors()) {
                errors.add(allError.getDefaultMessage());
            }
            map.addAttribute("errors", errors);
            return "account";
        } else {
            User user = mapper.map(userRequestDto, User.class);

            if (!user.getPassword().equals(confirmPassword)) {
                map.addAttribute("errorMsgs", "Passwords doesnt match");
                return "account";
            }

            user.setActive(false);
            user.setToken(UUID.randomUUID().toString());
            user.setTokenCreatedDate(LocalDateTime.now());
            userService.create(user);
            mailService.sendHtmlEmail(user.getEmail(),
                    "Welcome " + user.getName() + " " + user.getSurname(),
                    user,
                    "You have successfully register " + user.getName() +
                            " for activation please click on this url : http://localhost:8080/user/activate?token=" + user.getToken(),
                    "verify", locale);
        }
        return "redirect:/";
    }

    @GetMapping("/user/activate")
    public String activateUser(ModelMap map, @RequestParam String token) {
        Optional<User> user = userService.findByToken(UUID.fromString(token));
        if (user.isPresent()) {
            map.addAttribute("message", "User Does not exists");
            return "checkout";
        }
        User userFromDb = user.get();
        if (userFromDb.isActive()) {
            map.addAttribute("message", "User already exists");
            return "checkout";
        }

        userFromDb.setActive(true);
        userFromDb.setToken(null);
        userFromDb.setTokenCreatedDate(null);
        userService.save(userFromDb);
        map.addAttribute("message", "User activated, please login!");
        return "checkout";
    }


    @PostMapping("/editUser")
    public String editUser(@ModelAttribute UserRequestDto userRequestDto, @AuthenticationPrincipal CurrentUser currentUser) {
        if (!userRequestDto.getName().equals("")) {
            currentUser.getUser().setName(userRequestDto.getName());
        }
        if (!userRequestDto.getSurname().equals("")) {
            currentUser.getUser().setSurname(userRequestDto.getSurname());
        }
        if (!userRequestDto.getPhone().equals("")) {
            currentUser.getUser().setPhone(userRequestDto.getPhone());
        }
        if (!userRequestDto.getAddress().equals("")) {
            currentUser.getUser().setAddress(userRequestDto.getAddress());
        }
        userService.save(currentUser.getUser());
        return "redirect:/myAccount";
    }


}
