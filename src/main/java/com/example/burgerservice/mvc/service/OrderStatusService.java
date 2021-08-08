package com.example.burgerservice.mvc.service;

import com.example.burgerservice.mvc.domain.OrderStatus;

import java.util.List;

public interface OrderStatusService {

    void saveOrderStatus(OrderStatus orderStatus);

    void saveAllOrderStatus(List<OrderStatus> orderStatuses);

    List<OrderStatus> getAllStatuses();

    OrderStatus getOrderStatusById(String id);
}
