package com.example.burgerservice.rest.controller;

import com.example.burgerservice.mvc.utils.service.OrderServiceClient;
import com.example.burgerservice.rest.dto.IngredientDto;
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
        /*consumes = MediaType.APPLICATION_JSON_VALUE,*/
        produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class IngredientRestController {

    private final IngredientService ingredientService;
    private final OrderServiceClient orderServiceClient;

    @Autowired
    public IngredientRestController(IngredientService ingredientService,
                                    OrderServiceClient orderServiceClient) {

        this.ingredientService = ingredientService;
        this.orderServiceClient = orderServiceClient;
    }

    @PostMapping
    public ResponseEntity saveIngredient(@RequestBody @Valid IngredientDto ingredientDto) {
        log.info("response POST method by url /api/ingredient and {} into Request Body", ingredientDto);
        ingredientService.saveIngredient(ingredientDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<IngredientListWrapper> getAllIngredients() {
        log.info("response GET method by url /api/ingredient/all");
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @GetMapping("/checkresttemplate")
    public ResponseEntity<IngredientListWrapper> getAllIngredientsByRestTemplate() {
        return ResponseEntity.ok(orderServiceClient.getAllIngredients());
    }
}
