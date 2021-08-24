package com.example.burgerservice.rest.controller;

import com.example.burgerservice.rest.dto.IngredientTypeDto;
import com.example.burgerservice.rest.dto.IngredientTypeListWrapper;
import com.example.burgerservice.rest.service.IngredientTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ingredienttype")
@Slf4j
public class IngredientTypeRestController {

    private final IngredientTypeService ingredientTypeService;

    @Autowired
    public IngredientTypeRestController(IngredientTypeService ingredientTypeService) {
        this.ingredientTypeService = ingredientTypeService;
    }

    @GetMapping("/{ingredienttypeid}")
    public ResponseEntity<IngredientTypeDto> getIngredientType(@PathVariable("ingredienttypeid") String id,
                                                               @RequestParam String param) {
        System.out.println(param);
        log.info("response GET method by url /api/ingredientype/{}", id);
        return ResponseEntity.ok(ingredientTypeService.getIngredientType(id));
    }


    @PostMapping ResponseEntity saveIngredientType(@RequestBody IngredientTypeDto ingredientTypeDto) {
        log.info("response POST method by url /api/ingredienttype and {} into Request Body", ingredientTypeDto);
        ingredientTypeService.saveIngredientType(ingredientTypeDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<IngredientTypeListWrapper> getAllIngredientTypes() {
        return ResponseEntity.ok(ingredientTypeService.getAllIngredientTypes());
    }
}
