package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.Address;
import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final OrderServiceImpl orderService;

    @Autowired
    public HomeController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getHomePage() {
        return "home";
    }

    @PostMapping
    public String searchOrderByAddress(@Valid Address address, Model model) {
        List<BurgerOrder> result = orderService.findOrdersByAddress(address);
        model.addAttribute("searchResult", result);
        return "searchOrderResult";
    }

    @ModelAttribute("searchingAddress")
    public Address getAddress() {
        return new Address();
    }
}
