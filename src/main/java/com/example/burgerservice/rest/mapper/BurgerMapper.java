package com.example.burgerservice.rest.mapper;

import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.rest.dto.BurgerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        IngredientTypeMapper.class,
        OrderStatusMapper.class,
        BurgerOrderIdMapper.class})
public interface BurgerMapper {

    BurgerDto burgerDao2Dto(Burger burger);

    Burger burgerDto2Dao(BurgerDto burgerDto);
}
