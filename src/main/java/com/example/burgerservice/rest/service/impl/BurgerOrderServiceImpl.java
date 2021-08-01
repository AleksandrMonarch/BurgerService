package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.mvc.repository.OrderRepository;
import com.example.burgerservice.rest.dto.BurgerOrder;
import com.example.burgerservice.rest.mapper.BurgerOrderMapper;
import com.example.burgerservice.rest.service.BurgerOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Slf4j
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

    @Override
    public BurgerOrder updateBurgerOrder(String id, BurgerOrder newBurgerOrder) {

        Optional<com.example.burgerservice.mvc.domain.BurgerOrder> burgerOrder = orderRepository.findById(id);

        if (!burgerOrder.isPresent()) {
            log.error("the order with id {} is not found", id);
            throw new EntityNotFoundException();
        }

        return burgerOrderMapper.burgerOrderDao2Dto(
                orderRepository.save(
                        burgerOrderMapper.burgerOrderDto2Dao(newBurgerOrder))
        );
    }

    @Override
    public void deleteBurgerOrderById(String id) {

        Optional<com.example.burgerservice.mvc.domain.BurgerOrder> optionalBurgerOrder = orderRepository.findById(id);

        if (!optionalBurgerOrder.isPresent()) {
            log.error("deleting order with id = {} is not found", id);
            throw new EntityNotFoundException();
        }
        orderRepository.deleteById(id);
    }
}
