package com.example.burgerservice.mvc.repository;

import com.example.burgerservice.mvc.domain.OrderStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, String> {

    @Query("SELECT os FROM OrderStatus os LEFT JOIN FETCH os.burgerOrders WHERE os.id = :id")
    OrderStatus getOrderStatusById(String id);
}
