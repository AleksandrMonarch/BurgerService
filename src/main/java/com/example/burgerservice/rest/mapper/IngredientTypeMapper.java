package com.example.burgerservice.rest.mapper;

import com.example.burgerservice.mvc.domain.IngredientType;
import com.example.burgerservice.mvc.repository.IngredientTypeRepository;
import com.example.burgerservice.rest.dto.IngredientTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class IngredientTypeMapper {

    private final IngredientTypeRepository ingredientTypeRepository;

    @Autowired
    public IngredientTypeMapper(IngredientTypeRepository ingredientTypeRepository) {
        this.ingredientTypeRepository = ingredientTypeRepository;
    }

    public IngredientType map(String type) {
        return ingredientTypeRepository.findById(type).orElseThrow(EntityNotFoundException::new);
    }

    public String map(IngredientType type) {
        return type.getId();
    }

    public IngredientTypeDto ingredientTypeDao2Dto(IngredientType ingredientTypeDao) {
        IngredientTypeDto ingredientTypeDto =
                new IngredientTypeDto();
        ingredientTypeDto.setId(ingredientTypeDao.getId());
        ingredientTypeDto.setName(ingredientTypeDao.getName());
        return ingredientTypeDto;
    }

    public IngredientType ingredientTypeDto2Dao(IngredientTypeDto ingredientTypeDto) {
        IngredientType ingredientTypeDao = new IngredientType();
        ingredientTypeDao.setId(ingredientTypeDto.getId());
        ingredientTypeDao.setName(ingredientTypeDto.getName().toUpperCase());
        return ingredientTypeDao;
    }
}
