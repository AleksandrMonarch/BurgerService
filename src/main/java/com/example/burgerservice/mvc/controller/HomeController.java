package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.Address;
import com.example.burgerservice.mvc.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String  searchOrderByAddress(Address address, Model model) {
        model.addAttribute("searchResult", orderService.getOrdersByAddress(address));
        return "searchOrderResult";
    }

    @ModelAttribute("searchingAddress")
    public Address getNewAddress() {
        return new Address();
    }
}
