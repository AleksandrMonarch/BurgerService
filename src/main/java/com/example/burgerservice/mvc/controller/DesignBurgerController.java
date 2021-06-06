package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.Burger;
//import com.example.burgerservice.mvc.domain.Design;
import com.example.burgerservice.mvc.domain.Ingredient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/design")
public class DesignBurgerController {

    @GetMapping()
    public String getDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("CHS", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("BBQ", "Barbecue", Ingredient.Type.SOUSE),
                new Ingredient("BF", "Beef", Ingredient.Type.MEAT)
        );
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("burger", new Burger());

        return "design";
    }

    @PostMapping
    public String processDesign(Burger burger) {
        System.out.println(burger);
        return "home";
    }
}
