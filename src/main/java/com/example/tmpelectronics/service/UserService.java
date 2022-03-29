package com.example.tmpelectronics.service;


import com.example.tmpelectronics.entity.Role;
import com.example.tmpelectronics.entity.User;
import com.example.tmpelectronics.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(User user) {
            String encode = passwordEncoder.encode(user.getPassword());
            user.setPassword(encode);
            user.setRole(Role.USER);
            return userRepository.save(user);
    }

//    public List<String> errorMsgs(User user, String confirmPassword) {
//        List<String> errorMsgs = new ArrayList<>();
//        if(!user.getPassword().equals(confirmPassword)) {
//            errorMsgs.add("Passwords doesnt match");
//        }
//        return errorMsgs;
//    }

}
