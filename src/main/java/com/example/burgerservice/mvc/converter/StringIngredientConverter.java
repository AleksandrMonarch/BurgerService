package com.example.burgerservice.mvc.converter;

import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.mvc.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StringIngredientConverter implements Converter <String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    private final Map<String, Ingredient> ingredientMap;

    @Autowired
    public StringIngredientConverter(IngredientRepository ingredientRepository) {
        ingredientMap = new HashMap<>();
        this.ingredientRepository = ingredientRepository;
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
        for (Ingredient ingredient : ingredients) {
            ingredientMap.put(ingredient.getId(), ingredient);
        }
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientMap.get(id);
    }
}
