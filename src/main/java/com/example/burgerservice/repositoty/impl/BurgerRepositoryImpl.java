package com.example.burgerservice.repositoty.impl;

import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.repositoty.BurgerRepository;

import java.math.BigDecimal;
import java.util.*;

public class BurgerRepositoryImpl implements BurgerRepository {

    private static BigDecimal maxId = new BigDecimal("0");
    private final static Map<String, BurgerEntity> fakeBD = new HashMap<>();

    @Override
    public Burger create(Burger burger) {
        maxId = maxId.add(new BigDecimal("1"));
        burger.setId(maxId.toString());
        fakeBD.put(burger.getId(), new BurgerEntity(burger.getId(),burger, new Date()));
        return burger;
    }

    @Override
    public Burger findById(String id) {
        return fakeBD.containsKey(id) ? fakeBD.get(id).getBurger() : null;
    }

    @Override
    public long getBurgersCountFromDate(Date date) {

        return fakeBD.values().stream()
                .filter((burgerEntity -> burgerEntity.getDate().before(date))).count();
    }

    @Override
    public long countOfBurgersWithIngredient(String id) {
        long counter = 0;

        for (BurgerEntity burgerEntity : fakeBD.values()) {
            for (Ingredient ingredient : burgerEntity.getBurger().getIngredients()) {
                if (ingredient.getId().equals(id)) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
