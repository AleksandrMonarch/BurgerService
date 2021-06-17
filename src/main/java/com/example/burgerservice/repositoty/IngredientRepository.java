package com.example.burgerservice.repositoty;

import com.example.burgerservice.mvc.domain.Ingredient;

import java.util.Optional;

public interface IngredientRepository {

    Optional<Ingredient> findById(String id);
}
