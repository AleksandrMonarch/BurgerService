package com.example.burgerservice.rest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Builder
@Setter
@Getter
public class IngredientListWrapper {

    private List<Ingredient> ingredients;
}
