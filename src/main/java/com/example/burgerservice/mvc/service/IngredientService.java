package com.example.burgerservice.mvc.service;

import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.mvc.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public void saveIngredients(List<Ingredient> ingredients) {
        log.info("saving {} ingredients", ingredients.size());
        log.debug("saving ingredients: {}", ingredients);
        ingredientRepository.saveAll(ingredients);
    }

    public Iterable<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}
