package com.example.burgerservice.rest.service;

import com.example.burgerservice.rest.dto.BurgerOrder;

public interface BurgerOrderService {

    BurgerOrder getBurgerOrder(String id);
}
