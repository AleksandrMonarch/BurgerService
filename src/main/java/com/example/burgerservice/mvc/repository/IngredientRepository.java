package com.example.burgerservice.mvc.repository;

import com.example.burgerservice.mvc.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
