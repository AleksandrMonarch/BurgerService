package com.example.burgerservice.mvc.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class IngredientListWrapper {

    private List<Ingredient> ingredients;
}
