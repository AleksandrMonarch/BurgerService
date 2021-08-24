package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.constant.CacheConstants;
import com.example.burgerservice.mvc.repository.IngredientRepository;
import com.example.burgerservice.rest.dto.IngredientDto;
import com.example.burgerservice.rest.dto.IngredientListWrapper;
import com.example.burgerservice.rest.mapper.IngredientMapper;
import com.example.burgerservice.rest.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @CacheEvict(value = {CacheConstants.INGREDIENT_AND_TYPE,
            CacheConstants.INGREDIENT_AND_TYPE_WRAPPER,
            CacheConstants.INGREDIENTS_AND_TYPES},
            allEntries = true)
    public void saveIngredient(IngredientDto ingredientDto) {
        ingredientRepository.save(ingredientMapper.ingredientDto2Dao(ingredientDto));
    }

    @Override
    @Cacheable(cacheNames = CacheConstants.INGREDIENT_AND_TYPE_WRAPPER)
    public IngredientListWrapper getAllIngredients() {
         return IngredientListWrapper.builder().ingredients(
            StreamSupport
                    .stream(ingredientRepository.findAll().spliterator(), false)
                    .map(ingredientMapper::ingredientDao2Dto)
                    .collect(Collectors.toList()))
                 .build();
    }
}
