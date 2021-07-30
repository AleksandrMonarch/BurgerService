package com.example.burgerservice.rest.service;

import com.example.burgerservice.rest.dto.Ingredient;
import com.example.burgerservice.rest.dto.IngredientListWrapper;

import java.util.List;

public interface IngredientService {

    void saveIngredient(Ingredient ingredient);

    IngredientListWrapper getAllIngredients();
}
