package com.example.burgerservice.mvc.service.impl;

import com.example.burgerservice.constant.CacheConstants;
import com.example.burgerservice.mvc.domain.Address;
import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.repository.OrderRepository;
import com.example.burgerservice.mvc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @CacheEvict(value = {CacheConstants.BURGER_ORDER, CacheConstants.BURGER_ORDERS}, allEntries = true)
    public void saveOrder(BurgerOrder burgerOrder) {
        orderRepository.save(burgerOrder);
    }

    @Override
    @Cacheable(value = CacheConstants.BURGER_ORDERS)
    public List<BurgerOrder> getAllOrders() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<BurgerOrder> getOrdersByAddress(Address address) {
        return orderRepository.
                findBurgerOrderByAddress(
                        address.getStreet(), address.getCity(),
                        address.getState(), address.getZipNumber());
    }
}
