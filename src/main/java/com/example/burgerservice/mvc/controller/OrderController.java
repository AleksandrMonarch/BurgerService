package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.service.impl.BurgerOrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final BurgerOrderServiceImpl burgerOrderService;

    @Autowired
    public OrderController(BurgerOrderServiceImpl burgerOrderService) {
        this.burgerOrderService = burgerOrderService;
    }

    @GetMapping("/current")
    public String getOrderForm() {
        return "orderForm";
    }

    @PostMapping("/current")
    public String processBurgerOrder(@Valid BurgerOrder burgerOrder, Errors error, SessionStatus sessionStatus) {

        if (error.hasErrors()) {
            log.error("there are validation errors {}", error.getFieldErrors());
            return "orderForm";
        }
        burgerOrderService.saveBurgerOrder(burgerOrder);
        log.info("save the order {}", burgerOrder);
        sessionStatus.setComplete();
        return "menu";
    }

    @ModelAttribute
    public void addOrderInModel(Model model) {
        model.addAttribute("order", new BurgerOrder());
    }
}
