package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.*;
import com.example.burgerservice.mvc.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("burgerOrder")
public class DesignBurgerController {

    private final IngredientService ingredientService;


    @Autowired
    public DesignBurgerController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public String getDesignForm(Model model) {
        model.addAttribute("burger", new Burger());
        return "design";
    }

    @PostMapping
    public String processDesign(Burger burger, BurgerOrder burgerOrder, Errors error, Model model) {

        if (error.hasErrors()) {
            log.error("there are validation errors {}", error.getFieldErrors());
            return "design";
        }
        //сохранение бургера
        burgerOrder.addBurger(burger);
        log.info("save {} to order", burger);

        return "redirect:/orders/newOrder";
    }


    // Get List of Ingredients filtered by Type of them
    private List<Ingredient> filterByType(List<Ingredient> ingredients, IngredientType ingredientType) {
        return ingredients
                .stream()
                .filter(ingredient-> ingredient.getType().equals(ingredientType))
                .collect(Collectors.toList());
    }

    private List<Ingredient> getIngredientsList() {
        return ingredientService.getAllIngredients();
    }

//    Set up in model every type of Ingredient and list of matches Ingredients under this
    @ModelAttribute
    private void filterAtIngredients(Model model) {

        List<Ingredient> ingredients = getIngredientsList();

        Set<IngredientType> ingredientTypes = fetchAllIngredientTypes(ingredients);

        for (IngredientType type : ingredientTypes) {
            //here set up <IngredientType> and set up List<Ingredient> from filter-method
            model.addAttribute(type.getName().toUpperCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute("burgerOrder")
    private BurgerOrder getOrder() {
        return new BurgerOrder();
    }

//    Get Set of all types of Ingredients
    private Set<IngredientType> fetchAllIngredientTypes(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(Ingredient::getType)
                .collect(Collectors.toSet());
    }
}
