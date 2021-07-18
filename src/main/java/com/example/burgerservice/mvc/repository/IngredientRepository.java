package com.example.burgerservice.mvc.repository;

import com.example.burgerservice.mvc.domain.Ingredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

    @Query("SELECT i FROM Ingredient i JOIN FETCH i.type t")
    List<Ingredient> getAllIngredients();
}
