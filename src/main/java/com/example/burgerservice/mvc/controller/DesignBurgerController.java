package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.mvc.domain.Design;
import com.example.burgerservice.mvc.domain.Ingredient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/design")
public class DesignBurgerController {

    @GetMapping()
    public String getDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("CHS", "Chedder", Ingredient.Type.CHEESE),
                new Ingredient("BBQ", "Hiqnz", Ingredient.Type.SOUSE),
                new Ingredient("MT", "Beef", Ingredient.Type.MEAT)
        );
        model.addAttribute("Ingredients", ingredients);
        model.addAttribute("Burger", new Design());

        return "design";
    }

    @PostMapping
    public String processDesign(Design design) {
        System.out.println(design);
        return "home";
    }
}
