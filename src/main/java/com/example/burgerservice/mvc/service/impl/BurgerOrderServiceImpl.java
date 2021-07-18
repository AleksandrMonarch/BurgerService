package com.example.burgerservice.mvc.service.impl;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.repository.BurgerOrderRepository;
import com.example.burgerservice.mvc.service.BurgerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BurgerOrderServiceImpl implements BurgerOrderService {

    private final BurgerOrderRepository burgerOrderRepository;

    @Autowired
    public BurgerOrderServiceImpl(BurgerOrderRepository burgerOrderRepository) {
        this.burgerOrderRepository = burgerOrderRepository;
    }

    @Override
    public void saveBurgerOrder(BurgerOrder burgerOrder) {
        burgerOrderRepository.save(burgerOrder);
    }
}
