package com.example.burgerservice.mvc.service;

import com.example.burgerservice.mvc.domain.Address;
import com.example.burgerservice.mvc.domain.BurgerOrder;

import java.util.List;

public interface OrderService {

    void saveOrder(BurgerOrder burgerOrder);

    List<BurgerOrder> getAllOrders();

    List<BurgerOrder> getOrdersByAddress(Address address);
}
