package com.example.burgerservice.rest.mapper;


import com.example.burgerservice.mvc.domain.Ingredient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IngredientTypeMapper.class})
public interface IngredientMapper {

    Ingredient ingredientDto2Dao(com.example.burgerservice.rest.dto.Ingredient ingredient);

    com.example.burgerservice.rest.dto.Ingredient ingredientDao2Dto(Ingredient ingredient);
}
