package com.example.burgerservice.mvc.domain;

import lombok.Data;

import java.util.List;

@Data
public class Burger {

    private String id;
    private String name;
    private List<Ingredient> ingredients;
}
