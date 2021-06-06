package com.example.burgerservice.mvc.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Ingredient {

    private static List<Ingredient> ingredients = new ArrayList<>();

    private final String id;
    private final String name;
    private final Type type;

    public Ingredient(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
        ingredients.add(this);
    }

    public static enum Type {
        CHEESE,
        SOUSE,
        WRAP,
        MEAT,
        LETTUCE
    }

    public static List<Ingredient> getIngredients() {
        return ingredients;
    }
}
