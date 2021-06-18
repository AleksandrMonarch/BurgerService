package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.service.BurgerHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignBurgerController {

    @GetMapping()
    public String getDesignForm(Model model) {

        model.addAttribute("burger", new Burger());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Burger burger, Errors error, Model model) {

        if (error.hasErrors()) {
            log.error("there are validation errors {}", error.getFieldErrors());
            return "design";
        }
        log.info("save {} to order", burger);
        //сохранение бургера
        BurgerHistoryService.saveBurger(burger);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(ingredient-> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }

    private List<Ingredient> getIngredientsList() {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("CHS", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("PR", "Parmesan", Ingredient.Type.CHEESE),
                new Ingredient("BBQ", "Barbecue", Ingredient.Type.SOUSE),
                new Ingredient("CH", "Cheese", Ingredient.Type.SOUSE),
                new Ingredient("BF", "Beef", Ingredient.Type.MEAT),
                new Ingredient("PK", "Pork", Ingredient.Type.MEAT),
                new Ingredient("WS", "Wrap with sesame", Ingredient.Type.WRAP)
        );
        return ingredients;

    }

    @ModelAttribute
    private void filterAtIngredients(Model model) {

        List<Ingredient> ingredients = getIngredientsList();

        Ingredient.Type[] types = Ingredient.Type.values();

        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toUpperCase(), filterByType(ingredients, type));
        }
    }
}
