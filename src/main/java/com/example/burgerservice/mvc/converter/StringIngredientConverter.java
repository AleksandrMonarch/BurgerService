package com.example.burgerservice.mvc.converter;

import com.example.burgerservice.mvc.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StringIngredientConverter implements Converter <String, Ingredient> {

    @Override
    public Ingredient convert(String s) {
        for (Ingredient ingredient : Ingredient.getIngredients()) {
            if (ingredient.getId().equals(s)) return ingredient;
        }
        return null;
    }
}
