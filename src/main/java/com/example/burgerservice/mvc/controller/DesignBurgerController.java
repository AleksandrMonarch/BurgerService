package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.mvc.domain.IngredientType;
import com.example.burgerservice.mvc.repository.IngredientTypeRepository;
import com.example.burgerservice.mvc.service.BurgerHistoryService;
import com.example.burgerservice.mvc.service.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignBurgerController {

    private final IngredientService ingredientService;

    private final IngredientTypeRepository ingredientTypeRepository;

    @Autowired
    public DesignBurgerController(IngredientService ingredientService,
                                  IngredientTypeRepository ingredientTypeRepository) {
        this.ingredientService = ingredientService;
        this.ingredientTypeRepository = ingredientTypeRepository;
    }

    @GetMapping()
    public String getDesignForm(Model model) {
        model.addAttribute("burger", new Burger());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Burger burger, Errors error, Model model, BurgerHistoryService burgerHistoryService) {

        if (error.hasErrors()) {
            log.error("there are validation errors {}", error.getFieldErrors());
            return "design";
        }
        //сохранение бургера
        log.info("save {} to order", burgerHistoryService.saveBurger(burger));
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, IngredientType ingredientType) {
        return ingredients
                .stream()
                .filter(ingredient-> ingredient.getType().equals(ingredientType))
                .collect(Collectors.toList());
    }

    private Iterable<Ingredient> getIngredientsList() {
        return ingredientService.getAllIngredients();
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
        ingredientService.saveIngredients(ingredients);
    }

    @ModelAttribute
    private void filterAtIngredients(Model model) {

        Iterable<Ingredient> ingredients = getIngredientsList();

        List<Ingredient> result = new ArrayList<>();

        ingredients.forEach(result::add);

        Iterable<IngredientType> types = ingredientTypeRepository.findAll();

        for (IngredientType type : types) {
            model.addAttribute(type.getName().toUpperCase(), filterByType(result, type));
        }
    }
}
