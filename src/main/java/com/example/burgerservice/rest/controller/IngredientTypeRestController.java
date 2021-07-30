package com.example.burgerservice.rest.controller;

import com.example.burgerservice.rest.dto.IngredientType;
import com.example.burgerservice.rest.service.IngredientTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ingredienttype")
public class IngredientTypeRestController {

    private final IngredientTypeService ingredientTypeService;

    @Autowired
    public IngredientTypeRestController(IngredientTypeService ingredientTypeService) {
        this.ingredientTypeService = ingredientTypeService;
    }

    @GetMapping("/{ingredienttypeid}")
    public ResponseEntity<IngredientType> getIngredientType(@PathVariable("ingredientTypeId") String id) {
        return ResponseEntity.ok(ingredientTypeService.getIngredientType(id));
    }


    @PostMapping ResponseEntity saveIngredientType(@RequestBody IngredientType ingredientType) {
        ingredientTypeService.saveIngredientType(ingredientType);
        return ResponseEntity.ok().build();
    }
}
