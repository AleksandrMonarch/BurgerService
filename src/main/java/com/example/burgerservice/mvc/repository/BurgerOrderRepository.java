package com.example.burgerservice.mvc.repository;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import org.springframework.data.repository.CrudRepository;

public interface BurgerOrderRepository extends CrudRepository<BurgerOrder, String> {
}
