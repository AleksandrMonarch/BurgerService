package com.example.burgerservice.mvc.utils.service;

import com.example.burgerservice.rest.dto.*;
import org.springframework.http.ResponseEntity;

public interface OrderServiceClient {

    IngredientListWrapper getAllIngredients();

    void saveIngredient(IngredientDto ingredientDto);

    IngredientTypeDto getIngredientType(String id, String param);

    void saveIngredientType(IngredientTypeDto ingredientTypeDto);

    IngredientTypeListWrapper getAllIngredientTypes();

    BurgerOrderDto updateOrder(String id, BurgerOrderDto burgerOrderDto);

    BurgerOrderDto getOrder(String id);
}
