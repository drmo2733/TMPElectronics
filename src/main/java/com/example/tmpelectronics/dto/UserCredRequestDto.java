package com.example.tmpelectronics.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredRequestDto {

    private String name;
    private String surname;
    private String password;
    private String email;

}
