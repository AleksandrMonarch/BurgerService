package com.example.burgerservice.mvc.service.impl;

import com.example.burgerservice.constant.CacheConstants;
import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.mvc.repository.IngredientRepository;
import com.example.burgerservice.mvc.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @CacheEvict(cacheNames = {CacheConstants.INGREDIENTS_AND_TYPES,
            CacheConstants.INGREDIENT_AND_TYPE,
            CacheConstants.INGREDIENT_AND_TYPE_WRAPPER},
            allEntries = true)
    public void saveIngredients(List<Ingredient> ingredients) {
        log.info("saving {} ingredients", ingredients.size());
        log.debug("saving ingredients: {}", ingredients);
        ingredientRepository.saveAll(ingredients);
    }

    @Override
    @Cacheable(cacheNames = CacheConstants.INGREDIENTS_AND_TYPES)
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.getAllIngredients();
    }

    @Override
    @Cacheable(value = CacheConstants.INGREDIENT_AND_TYPE, key = "#id")
    public Ingredient getIngredientById(String id) {
        return ingredientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
