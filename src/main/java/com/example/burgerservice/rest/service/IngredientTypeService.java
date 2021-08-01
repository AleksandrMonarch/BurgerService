package com.example.burgerservice.rest.service;

import com.example.burgerservice.rest.dto.IngredientType;
import com.example.burgerservice.rest.dto.IngredientTypeListWrapper;

public interface IngredientTypeService {

    IngredientType getIngredientType(String id);

    void saveIngredientType(IngredientType ingredientType);

    IngredientTypeListWrapper getAllIngredientTypes();
}
