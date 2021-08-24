package com.example.burgerservice.rest.service;

import com.example.burgerservice.rest.dto.IngredientDto;
import com.example.burgerservice.rest.dto.IngredientListWrapper;

public interface IngredientService {

    void saveIngredient(IngredientDto ingredientDto);

    IngredientListWrapper getAllIngredients();
}
