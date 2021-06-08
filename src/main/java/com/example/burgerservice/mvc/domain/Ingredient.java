package com.example.burgerservice.mvc.domain;

import lombok.Data;

import java.util.Map;
import java.util.TreeMap;

@Data
public class Ingredient {

    private static final Map<String, Ingredient> ingredients = new TreeMap<>();

    private final String id;
    private final String name;
    private final Type type;

    public Ingredient(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
        if (!ingredients.containsKey(id)) ingredients.put(id, this);
    }

    public static enum Type {
        CHEESE,
        SOUSE,
        WRAP,
        MEAT,
        LETTUCE
    }

    public static Ingredient getIngredient(String key) {
        return ingredients.get(key);
    }
}
