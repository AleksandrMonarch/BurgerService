package com.example.burgerservice.rest.controller;

import com.example.burgerservice.rest.dto.BurgerOrder;
import com.example.burgerservice.rest.service.BurgerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class BurgerOrderRestController {

    private final BurgerOrderService burgerOrderService;

    @Autowired
    public BurgerOrderRestController(BurgerOrderService burgerOrderService) {
        this.burgerOrderService = burgerOrderService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<BurgerOrder> getOrder(@PathVariable("orderId") String id) {
        return ResponseEntity.ok(burgerOrderService.getBurgerOrder(id));
    }
}
