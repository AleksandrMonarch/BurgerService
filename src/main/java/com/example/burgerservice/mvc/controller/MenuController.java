package com.example.burgerservice.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @GetMapping
    public String getMenu() {
        return "menu";
    }
}
