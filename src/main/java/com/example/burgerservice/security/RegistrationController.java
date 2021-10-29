package com.example.burgerservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registryForm() {
        return "registerform";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        repository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
