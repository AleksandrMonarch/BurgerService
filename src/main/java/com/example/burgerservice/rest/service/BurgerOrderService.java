package com.example.burgerservice.rest.service;

import com.example.burgerservice.rest.dto.BurgerOrderDto;

public interface BurgerOrderService {

    BurgerOrderDto getBurgerOrder(String id);

    BurgerOrderDto updateBurgerOrder(String id, BurgerOrderDto newBurgerOrderDto);

    void deleteBurgerOrderById(String id);

    BurgerOrderDto cancelOrder(String id);
}
