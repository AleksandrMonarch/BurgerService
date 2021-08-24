package com.example.burgerservice.rest.service;

import com.example.burgerservice.rest.dto.IngredientTypeDto;
import com.example.burgerservice.rest.dto.IngredientTypeListWrapper;

public interface IngredientTypeService {

    IngredientTypeDto getIngredientType(String id);

    void saveIngredientType(IngredientTypeDto ingredientTypeDto);

    IngredientTypeListWrapper getAllIngredientTypes();
}
