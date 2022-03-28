package com.example.tmpelectronics.service;


import com.example.tmpelectronics.entity.Role;
import com.example.tmpelectronics.entity.User;
import com.example.tmpelectronics.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


}
