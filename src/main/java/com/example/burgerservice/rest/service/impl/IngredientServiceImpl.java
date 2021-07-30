package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.mvc.repository.IngredientRepository;
import com.example.burgerservice.rest.dto.Ingredient;
import com.example.burgerservice.rest.dto.IngredientListWrapper;
import com.example.burgerservice.rest.mapper.IngredientMapper;
import com.example.burgerservice.rest.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service("restIngredientService")
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientMapper = ingredientMapper;
    }

    @Override
    public void saveIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredientMapper.ingredientDto2Dao(ingredient));
    }

    @Override
    public IngredientListWrapper getAllIngredients() {
         return IngredientListWrapper.builder().ingredients(
            StreamSupport
                    .stream(ingredientRepository.findAll().spliterator(), false)
                    .map(ingredientMapper::ingredientDao2Dto)
                    .collect(Collectors.toList()))
                 .build();
    }
}
