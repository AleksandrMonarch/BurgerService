package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.mvc.domain.IngredientType;
import com.example.burgerservice.mvc.service.impl.IngredientServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("burgerOrder")
public class DesignBurgerController {

    private final IngredientServiceImpl ingredientServiceImpl;

    @Autowired
    public DesignBurgerController(IngredientServiceImpl ingredientServiceImpl) {
        this.ingredientServiceImpl = ingredientServiceImpl;
    }

    @GetMapping()
    public String getDesignForm(Model model) {
        model.addAttribute("burger", new Burger());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Burger burger, Errors error,
                                @ModelAttribute BurgerOrder burgerOrder) {

        if (error.hasErrors()) {
            log.error("there are validation errors {}", error.getFieldErrors());
            return "design";
        }
        //сохранение бургера
        log.info("save {} to order", burger);
        burgerOrder.addBurger(burger);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, IngredientType ingredientType) {
        return ingredients
                .stream()
                .filter(ingredient-> ingredient.getType().equals(ingredientType))
                .collect(Collectors.toList());
    }

    private Iterable<Ingredient> getIngredientsList() {
        return ingredientServiceImpl.getAllIngredients();
    }

    @PostConstruct
    private void saveAllIngredients() {

        IngredientType cheeseType = new IngredientType("CS", "CHEESE");
        IngredientType souseType = new IngredientType("SOU", "SOUSE");
        IngredientType meatType = new IngredientType("MT", "MEAT");
        IngredientType wrapType = new IngredientType("WP", "WRAP");

        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("CHS", "Cheddar", cheeseType),
                new Ingredient("PR", "Parmesan", cheeseType),
                new Ingredient("BBQ", "Barbecue", souseType),
                new Ingredient("CH", "Cheese", souseType),
                new Ingredient("BF", "Beef", meatType),
                new Ingredient("PK", "Pork", meatType),
                new Ingredient("WS", "Wrap with sesame", wrapType)
        );
        ingredientServiceImpl.saveIngredients(ingredients);
    }

    @ModelAttribute
    private void filterAtIngredients(Model model) {

        List<Ingredient> ingredients = ingredientServiceImpl.getAllIngredients();

        Set<IngredientType> ingredientTypes = fetchAllIngredientTypes(ingredients);

        for (IngredientType type : ingredientTypes) {
            model.addAttribute(type.getName().toUpperCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute("burgerOrder")
    private BurgerOrder getOrder() {
        return new BurgerOrder();
    }

    private Set<IngredientType> fetchAllIngredientTypes(Iterable<Ingredient> ingredients) {
        return StreamSupport.stream(ingredients.spliterator(), false)
                .map(Ingredient::getType)
                .collect(Collectors.toSet());
    }
}
