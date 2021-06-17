package com.example.burgerservice.repositoty.impl;

import com.example.burgerservice.mvc.domain.Burger;
import lombok.Data;

import java.util.Date;

@Data
public class BurgerEntity {

    private static long countOfBurgers = 0;

    private String id;
    private Burger burger;
    private Date date;

    public BurgerEntity(String id, Burger burger, Date date) {
        this.id = id;
        this.burger = burger;
        this.date = date;
        countOfBurgers++;
    }

    public Burger getBurger() {
        return burger;
    }
}
