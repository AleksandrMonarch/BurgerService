package com.example.burgerservice.mvc.repository;

import com.example.burgerservice.mvc.domain.Burger;
import org.springframework.data.repository.CrudRepository;

public interface BurgerRepository extends CrudRepository<Burger, String> {

}
