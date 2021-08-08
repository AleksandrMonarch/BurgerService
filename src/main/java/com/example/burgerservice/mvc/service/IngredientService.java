package com.example.burgerservice.mvc.service;

import com.example.burgerservice.mvc.domain.Ingredient;

import java.util.List;

public interface IngredientService {

    void saveIngredients(List<Ingredient> ingredients);

    List<Ingredient> getAllIngredients();

    Ingredient getIngredientById(String id);
}
