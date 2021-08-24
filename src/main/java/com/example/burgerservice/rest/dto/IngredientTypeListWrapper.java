package com.example.burgerservice.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class IngredientTypeListWrapper {

    private List<IngredientTypeDto> ingredientTypes;
}
