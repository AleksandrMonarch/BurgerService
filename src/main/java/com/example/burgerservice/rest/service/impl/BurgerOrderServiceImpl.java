package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.constant.CacheConstants;
import com.example.burgerservice.mvc.repository.OrderRepository;
import com.example.burgerservice.mvc.repository.OrderStatusRepository;
import com.example.burgerservice.rest.dto.BurgerOrder;
import com.example.burgerservice.rest.mapper.BurgerOrderMapper;
import com.example.burgerservice.rest.service.BurgerOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Slf4j
public class BurgerOrderServiceImpl implements BurgerOrderService {

    private final OrderRepository orderRepository;
    private final BurgerOrderMapper burgerOrderMapper;
    private final OrderStatusRepository orderStatusRepository;

    @Autowired
    public BurgerOrderServiceImpl(OrderRepository orderRepository,
                                  BurgerOrderMapper burgerOrderMapper,
                                  OrderStatusRepository orderStatusRepository) {

        this.orderRepository = orderRepository;
        this.burgerOrderMapper = burgerOrderMapper;
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    @Cacheable(value = CacheConstants.BURGER_ORDER, key = "#id")
    public BurgerOrder getBurgerOrder(String id) {
//        return burgerOrderMapper.burgerOrderDao2Dto(
//                orderRepository
//                        .findById(id)
//                        .orElseThrow(EntityNotFoundException::new));

        com.example.burgerservice.mvc.domain.BurgerOrder burgerOrder1 =
                orderRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);

        BurgerOrder burgerOrder = burgerOrderMapper.burgerOrderDao2Dto(burgerOrder1);
        return burgerOrder;
    }

    @Override
    @CacheEvict(value = CacheConstants.BURGER_ORDER, allEntries = true)
    public BurgerOrder updateBurgerOrder(String id, BurgerOrder newBurgerOrder) {


        // TODO: 08.08.2021 is there a mistake? 
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

    @Override
    public BurgerOrder cancelOrder(String id) {
        com.example.burgerservice.mvc.domain.BurgerOrder burgerOrder =
                orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        burgerOrder.addOrderStatus(orderStatusRepository.getOrderStatusById("CL"));
        orderRepository.save(burgerOrder);
        return burgerOrderMapper.burgerOrderDao2Dto(burgerOrder);
    }
}
