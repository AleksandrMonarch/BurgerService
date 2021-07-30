package com.example.burgerservice.mvc.service;

import com.example.burgerservice.mvc.domain.Ingredient;

import java.util.List;

public interface IngredientService {

    void saveIngredients(List<Ingredient> ingredients);

    public List<Ingredient> getAllIngredients();

    public Ingredient getIngredientById(String id);
}
