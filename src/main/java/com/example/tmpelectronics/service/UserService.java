package com.example.tmpelectronics.service;


import com.example.tmpelectronics.entity.Role;
import com.example.tmpelectronics.entity.User;
import com.example.tmpelectronics.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void create(User user) {
            String encode = passwordEncoder.encode(user.getPassword());
            user.setPassword(encode);
            user.setRole(Role.USER);
            userRepository.save(user);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByToken(UUID fromString) {
        return userRepository.findByToken(fromString);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


}
