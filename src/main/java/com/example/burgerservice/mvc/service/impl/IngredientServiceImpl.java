package com.example.burgerservice.mvc.service.impl;

import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.mvc.repository.IngredientRepository;
import com.example.burgerservice.mvc.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void saveIngredients(List<Ingredient> ingredients) {
        log.info("saving {} ingredients", ingredients.size());
        log.debug("saving ingredients: {}", ingredients);
        ingredientRepository.saveAll(ingredients);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.getAllIngredients();
    }

    @Override
    public Ingredient getIngredientById(String id) {
        return ingredientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
