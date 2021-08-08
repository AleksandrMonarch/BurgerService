package com.example.burgerservice.mvc.repository;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<BurgerOrder, String> {

    @Query("SELECT o FROM BurgerOrder o JOIN FETCH o.address a " +
            "WHERE a.street = :street AND a.city = :city AND a.state = :state AND a.zipNumber = :zipNumber")
    List<BurgerOrder> findBurgerOrderByAddress(String street, String city, String state, String zipNumber);

    @Query("SELECT o FROM BurgerOrder o INNER JOIN FETCH o.orderStatus")
    List<BurgerOrder> findAllOrders();
}
