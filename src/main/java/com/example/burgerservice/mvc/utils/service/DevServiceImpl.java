package com.example.burgerservice.mvc.utils.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!local")
public class DevServiceImpl implements DevService {

    @Override
    public void checkProfile() {
        System.out.println("Not local profile");
    }
}