package com.example.burgerservice.rest.mapper;

import com.example.burgerservice.rest.dto.Burger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = IngredientTypeMapper.class)
public interface BurgerMapper {

    Burger burgerDao2Dto(com.example.burgerservice.mvc.domain.Burger burger);

    com.example.burgerservice.mvc.domain.Burger burgerDto2Dao(Burger burger);
}
