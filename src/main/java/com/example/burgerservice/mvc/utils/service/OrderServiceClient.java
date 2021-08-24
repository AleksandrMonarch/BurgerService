package com.example.burgerservice.mvc.utils.service;

import com.example.burgerservice.rest.dto.IngredientListWrapper;

import java.util.List;

public interface OrderServiceClient {

    IngredientListWrapper getAllIngredients();
}
