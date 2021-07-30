package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.rest.dto.IngredientType;
import com.example.burgerservice.rest.mapper.IngredientTypeMapper;
import com.example.burgerservice.rest.service.IngredientTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service("restIngredientTypeService")
public class IngredientTypeServiceImpl implements IngredientTypeService {

    private final IngredientTypeMapper ingredientTypeMapper;

    @Autowired
    public IngredientTypeServiceImpl(IngredientTypeMapper ingredientTypeMapper) {
        this.ingredientTypeMapper = ingredientTypeMapper;
    }

    @Override
    public IngredientType getIngredientType(String id) {
        return null;
    }
}
