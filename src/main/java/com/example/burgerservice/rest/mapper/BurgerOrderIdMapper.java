package com.example.burgerservice.rest.mapper;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class BurgerOrderIdMapper {

    private final OrderRepository orderRepository;

    @Autowired
    public BurgerOrderIdMapper(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String map(BurgerOrder burgerOrder) {
        return burgerOrder.getId();
    }

    public BurgerOrder map(String burgerOrder) {
        return orderRepository.findById(burgerOrder).orElseThrow(EntityNotFoundException::new);
    }
}
