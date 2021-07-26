package com.example.burgerservice.mvc.repository;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<BurgerOrder, String> {

    List<BurgerOrder> findBurgerOrderByAddress_Id(Integer id);
}
