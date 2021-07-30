package com.example.burgerservice.rest.controller;

import com.example.burgerservice.rest.dto.Burger;
import com.example.burgerservice.rest.service.BurgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/burger")
public class BurgerController {

    private final BurgerService burgerService;

    @Autowired
    public BurgerController(BurgerService burgerService) {
        this.burgerService = burgerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Burger> getBurgerById(@PathVariable String id) {
        return ResponseEntity.ok(burgerService.getBurgerById(id));
    }
}
