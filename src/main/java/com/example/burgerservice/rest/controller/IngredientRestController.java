package com.example.burgerservice.rest.controller;

import com.example.burgerservice.rest.dto.Ingredient;
import com.example.burgerservice.rest.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ingredient")
public class IngredientRestController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientRestController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity saveIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.saveIngredient(ingredient);
        return ResponseEntity.ok().build();
    }
}
