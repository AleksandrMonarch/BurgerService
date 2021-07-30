package com.example.burgerservice.rest.service;

import com.example.burgerservice.rest.dto.Burger;


public interface BurgerService {

    Burger getBurgerById(String id);
}
