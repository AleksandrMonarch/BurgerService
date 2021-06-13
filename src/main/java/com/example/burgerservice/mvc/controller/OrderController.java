package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/current")
    public String getOrderForm(Model model) {
        model.addAttribute("order", new BurgerOrder());
        return "orderForm";
    }

    @PostMapping("/current")
    public String processBurgerOrder(BurgerOrder burgerOrder) {
        log.info("save the order {}", burgerOrder);
        return "menu";
    }
}
