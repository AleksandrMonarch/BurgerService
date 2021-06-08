package com.example.burgerservice.mvc.converter;

import com.example.burgerservice.mvc.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringIngredientConverter implements Converter <String, Ingredient> {

    @Override
    public Ingredient convert(String s) {
        return Ingredient.getIngredient(s);
    }
}
