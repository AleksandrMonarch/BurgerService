package com.example.burgerservice.rest.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientTypeListWrapper {

    private List<IngredientTypeDto> ingredientTypes;
}
