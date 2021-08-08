package com.example.burgerservice.rest.mapper;

import com.example.burgerservice.mvc.domain.OrderStatus;
import com.example.burgerservice.mvc.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class OrderStatusMapper {

    private final OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderStatusMapper(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    public String map(OrderStatus orderStatus) {
        return orderStatus.getId();
    }

    public OrderStatus map(String status) {
        return orderStatusRepository.findById(status).orElseThrow(EntityNotFoundException::new);
    }
}
