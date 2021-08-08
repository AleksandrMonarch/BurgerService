package com.example.burgerservice.mvc.service.impl;

import com.example.burgerservice.mvc.domain.OrderStatus;
import com.example.burgerservice.mvc.repository.OrderStatusRepository;
import com.example.burgerservice.mvc.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public void saveOrderStatus(OrderStatus orderStatus) {
        orderStatusRepository.save(orderStatus);
    }

    @Override
    public void saveAllOrderStatus(List<OrderStatus> orderStatuses) {
        orderStatusRepository.saveAll(orderStatuses);
    }

    @Override
    public List<OrderStatus> getAllStatuses() {
        return StreamSupport.stream(orderStatusRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public OrderStatus getOrderStatusById(String id) {
        return orderStatusRepository.getOrderStatusById(id);
    }
}
