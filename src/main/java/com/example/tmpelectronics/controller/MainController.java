package com.example.tmpelectronics.controller;

import com.example.tmpelectronics.security.CurrentUser;
import com.example.tmpelectronics.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/account")
    public String account() {
        return "account";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    @GetMapping("/myAccount")
    public String myAccount(@AuthenticationPrincipal CurrentUser currentUser) {
        return "my-account";
    }

}
