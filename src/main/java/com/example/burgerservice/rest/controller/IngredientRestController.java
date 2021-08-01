package com.example.burgerservice.rest.controller;

import com.example.burgerservice.rest.dto.Ingredient;
import com.example.burgerservice.rest.dto.IngredientListWrapper;
import com.example.burgerservice.rest.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class IngredientRestController {

    private final IngredientService ingredientService;

    @Autowired
    public IngredientRestController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity saveIngredient(@RequestBody @Valid Ingredient ingredient) {
        log.info("response POST method by url /api/ingredient and {} into Request Body", ingredient);
        ingredientService.saveIngredient(ingredient);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<IngredientListWrapper> getAllIngredients() {
        log.info("response GET method by url /api/ingredient/all");
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }
}
