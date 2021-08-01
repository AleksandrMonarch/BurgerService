package com.example.burgerservice.job;


import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@Slf4j
public class OrderCheckJob {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderCheckJob(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Scheduled(fixedDelay = 5000, initialDelay = 10000)
    public void reportCurrentDate() {

        log.info("job successful started");

        List<BurgerOrder> burgerOrders = StreamSupport.stream(orderRepository.findAll().spliterator(), true)
                .filter(burgerOrder -> burgerOrder.getCreatedAt().isBefore(LocalDateTime.now().minusDays(3L)))
                .collect(Collectors.toList());

        orderRepository.deleteAll(burgerOrders);
    }
}
