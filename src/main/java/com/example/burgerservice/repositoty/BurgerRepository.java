package com.example.burgerservice.repositoty;

import com.example.burgerservice.mvc.domain.Burger;

import java.util.Date;

public interface BurgerRepository {

    Burger create(Burger burger);

    Burger findById(String id);

    long getBurgersCountFromDate(Date date);

    long countOfBurgersWithIngredient(String id);
}
