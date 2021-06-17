package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/current")
    public String getOrderForm(Model model) {
        return "orderForm";
    }

    @PostMapping("/current")
    public String processBurgerOrder(@Valid BurgerOrder burgerOrder, Errors error) {

        if (error.hasErrors()) {
            log.error("there are validation errors {}", error.getFieldErrors());
            return "orderForm";
        }
        log.info("save the order {}", burgerOrder);
        return "menu";
    }

    @ModelAttribute
    public void addOrderInModel(Model model) {
        model.addAttribute("order", new BurgerOrder());
    }
}
