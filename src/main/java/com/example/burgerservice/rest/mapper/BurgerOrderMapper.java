package com.example.burgerservice.rest.mapper;

import com.example.burgerservice.rest.dto.BurgerOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BurgerOrderMapper {

    BurgerOrder burgerOrderDao2Dto(com.example.burgerservice.mvc.domain.BurgerOrder burgerOrder);

    com.example.burgerservice.mvc.domain.BurgerOrder burgerOrderDto2Dao(BurgerOrder burgerOrder);
}
