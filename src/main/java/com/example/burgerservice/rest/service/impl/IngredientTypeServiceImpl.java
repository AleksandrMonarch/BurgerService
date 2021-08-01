package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.mvc.repository.IngredientTypeRepository;
import com.example.burgerservice.rest.dto.IngredientType;
import com.example.burgerservice.rest.dto.IngredientTypeListWrapper;
import com.example.burgerservice.rest.mapper.IngredientTypeMapper;
import com.example.burgerservice.rest.service.IngredientTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service("restIngredientTypeService")
public class IngredientTypeServiceImpl implements IngredientTypeService {

    private final IngredientTypeMapper ingredientTypeMapper;
    private final IngredientTypeRepository ingredientTypeRepository;

    @Autowired
    public IngredientTypeServiceImpl(IngredientTypeMapper ingredientTypeMapper,
                                     IngredientTypeRepository ingredientTypeRepository) {
        this.ingredientTypeMapper = ingredientTypeMapper;
        this.ingredientTypeRepository = ingredientTypeRepository;
    }

    @Override
    public IngredientType getIngredientType(String id) {
        return ingredientTypeMapper.ingredientTypeDao2Dto(ingredientTypeMapper.map(id));
    }

    @Override
    public void saveIngredientType(IngredientType ingredientType) {
        ingredientTypeRepository.save(ingredientTypeMapper.ingredientTypeDto2Dao(ingredientType));
    }

    public IngredientTypeListWrapper getAllIngredientTypes() {
        return IngredientTypeListWrapper.builder().ingredientTypes(
                StreamSupport.stream(ingredientTypeRepository.findAll().spliterator(), false)
                        .map(ingredientTypeMapper::ingredientTypeDao2Dto)
                        .collect(Collectors.toList())
        ).build();
    }
}
