package com.example.burgerservice.mvc.repository;

import com.example.burgerservice.mvc.domain.Address;
import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.domain.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BurgerRepository extends CrudRepository<Burger, String> {

}
