package com.example.burgerservice.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String userName;

    private String fullName;

    private String password;

    private String city;

    private String street;

    private String phoneNumber;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(userName, fullName, passwordEncoder.encode(password), city, street ,phoneNumber);
    }
}
