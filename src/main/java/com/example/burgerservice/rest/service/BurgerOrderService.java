package com.example.burgerservice.rest.service;

import com.example.burgerservice.rest.dto.BurgerOrder;

public interface BurgerOrderService {

    BurgerOrder getBurgerOrder(String id);

    BurgerOrder updateBurgerOrder(String id, BurgerOrder newBurgerOrder);

    void deleteBurgerOrderById(String id);

    BurgerOrder cancelOrder(String id);
}
