package com.example.burgerservice.rest.controller;

import com.example.burgerservice.rest.dto.Ingredient;
import com.example.burgerservice.rest.dto.IngredientListWrapper;
import com.example.burgerservice.rest.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/api/ingredient",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class IngredientRestController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientRestController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity saveIngredient(@RequestBody @Valid Ingredient ingredient) {
        ingredientService.saveIngredient(ingredient);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<IngredientListWrapper> getAllIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }
}
