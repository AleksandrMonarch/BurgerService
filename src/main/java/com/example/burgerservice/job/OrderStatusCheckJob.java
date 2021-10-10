package com.example.burgerservice.job;

import com.example.burgerservice.annotation.Log;
import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.domain.OrderStatus;
import com.example.burgerservice.mvc.repository.OrderRepository;
import com.example.burgerservice.mvc.repository.OrderStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@Slf4j
public class OrderStatusCheckJob {

    private final OrderRepository orderRepository;
    private final OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderStatusCheckJob(OrderRepository orderRepository,
                               OrderStatusRepository orderStatusRepository) {
        this.orderRepository = orderRepository;
        this.orderStatusRepository = orderStatusRepository;
    }

    @Log
    @Scheduled(fixedDelay = 60000, initialDelay = 5000)
    public void changeOrdersStatuses() {

        OrderStatus inProgress = orderStatusRepository.getOrderStatusById("IP");
        OrderStatus done = orderStatusRepository.getOrderStatusById("DN");

        List<BurgerOrder> burgerOrders = StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        for (BurgerOrder burgerOrder : burgerOrders) {
            switch (burgerOrder.getOrderStatus().getId()) {
                case "CR":
                    burgerOrder.addOrderStatus(inProgress);
                    orderRepository.save(burgerOrder);
                    log.info("Change status of order {} from CR to {}", burgerOrder.getId(), inProgress.getId());
                    break;
                case "IP":
                    burgerOrder.addOrderStatus(done);
                    orderRepository.save(burgerOrder);
                    log.info("Change status of order {} from CR to {}", burgerOrder.getId(), done.getId());
                    break;
                case "DN":
                    orderRepository.delete(burgerOrder);
                    log.info("delete order {} from database", burgerOrder.getId());
                    break;
            }
        }
    }
}
