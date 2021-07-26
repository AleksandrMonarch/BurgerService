package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.*;
import com.example.burgerservice.mvc.service.AddressService;
import com.example.burgerservice.mvc.service.impl.IngredientServiceImpl;
import com.example.burgerservice.mvc.service.impl.OrderServiceImpl;
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

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("burgerOrder")
public class DesignBurgerController {

    private final IngredientServiceImpl ingredientServiceImpl;
    private final OrderServiceImpl orderService;
    private final AddressService addressService;

    @Autowired
    public DesignBurgerController(IngredientServiceImpl ingredientService, OrderServiceImpl orderService, AddressService addressService) {
        this.ingredientServiceImpl = ingredientService;
        this.orderService = orderService;
        this.addressService = addressService;
    }

    @GetMapping
    public String getDesignForm(Model model) {
        model.addAttribute("burger", new Burger());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Burger burger, @ModelAttribute BurgerOrder burgerOrder, Errors error) {

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

        Burger burger1 = new Burger();
        Burger burger2 = new Burger();
        Burger burger3 = new Burger();
        Burger burger4 = new Burger();

        burger1.setName("First burger");
        burger1.addIngredient(ingredients.get(6));
        burger1.addIngredient(ingredients.get(5));
        burger1.addIngredient(ingredients.get(0));

        burger2.setName("Second burger");
        burger2.addIngredient(ingredients.get(6));
        burger2.addIngredient(ingredients.get(4));
        burger2.addIngredient(ingredients.get(1));

        burger3.setName("Third burger");
        burger3.addIngredient(ingredients.get(6));
        burger3.addIngredient(ingredients.get(4));
        burger3.addIngredient(ingredients.get(1));
        burger3.addIngredient(ingredients.get(2));

        burger4.setName("Fourth burger");
        burger4.addIngredient(ingredients.get(6));
        burger4.addIngredient(ingredients.get(5));
        burger4.addIngredient(ingredients.get(1));
        burger4.addIngredient(ingredients.get(3));

        BurgerOrder order1 = new BurgerOrder();
        order1.setOrderName("First order");
        order1.addBurger(burger1);
        order1.addBurger(burger2);

        Address address1 = new Address();
        address1.setCity("Saint-Petersburg");
        address1.setState("Saint-Petersburg");
        address1.setStreet("Nevsky avenue");
        address1.setZipNumber("195999");
        address1.setId(addressService.findAddressIdByStreetAndCityAndStateAndZipNumber(address1));

        order1.addAddress(address1);

        BurgerOrder order2 = new BurgerOrder();

        order2.setOrderName("Second order");
        order2.addBurger(burger3);
        order2.addBurger(burger4);

        Address address2 = new Address();
        address2.setCity("Moscow");
        address2.setState("Moscow");
        address2.setStreet("Red Square");
        address2.setZipNumber("199555");

        address2.setId(addressService.findAddressIdByStreetAndCityAndStateAndZipNumber(address2));

        order2.addAddress(address2);

        orderService.saveOrder(order1);
        orderService.saveOrder(order2);
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
