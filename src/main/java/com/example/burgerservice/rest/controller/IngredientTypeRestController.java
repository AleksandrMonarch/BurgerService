package com.example.burgerservice.rest.controller;

import com.example.burgerservice.rest.dto.IngredientType;
import com.example.burgerservice.rest.service.IngredientTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ingredientType")
public class IngredientTypeRestController {

    private final IngredientTypeService ingredientTypeService;

    @Autowired
    public IngredientTypeRestController(IngredientTypeService ingredientTypeService) {
        this.ingredientTypeService = ingredientTypeService;
    }

    @GetMapping("/{ingredientTypeId}")
    public ResponseEntity<IngredientType> getIngredientType(@PathVariable("ingredientTypeId") String id) {
        return ResponseEntity.ok(ingredientTypeService.getIngredientType(id));
    }
}
