package com.example.burgerservice.mvc.domain;

import lombok.Data;

@Data
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        CHEESE,
        SOUSE,
        WRAP,
        MEAT,
        LETTUCE
    }
}
