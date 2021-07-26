package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.mvc.repository.OrderRepository;
import com.example.burgerservice.rest.dto.BurgerOrder;
import com.example.burgerservice.rest.mapper.BurgerOrderMapper;
import com.example.burgerservice.rest.service.BurgerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class BurgerOrderServiceImpl implements BurgerOrderService {

    private final OrderRepository orderRepository;
    private final BurgerOrderMapper burgerOrderMapper;

    @Autowired
    public BurgerOrderServiceImpl(OrderRepository orderRepository, BurgerOrderMapper burgerOrderMapper) {
        this.orderRepository = orderRepository;
        this.burgerOrderMapper = burgerOrderMapper;
    }

    @Override
    public BurgerOrder getBurgerOrder(String id) {
        return burgerOrderMapper.burgerOrderDao2Dto(
                orderRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new));
    }
}
