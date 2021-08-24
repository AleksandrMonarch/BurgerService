package com.example.burgerservice.rest.service;

import com.example.burgerservice.rest.dto.BurgerDto;


public interface BurgerService {

    BurgerDto getBurgerById(String id);
}
