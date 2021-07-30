package com.example.burgerservice.rest.controller;

import com.example.burgerservice.rest.dto.BurgerOrder;
import com.example.burgerservice.rest.service.BurgerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/order", consumes = MediaType.APPLICATION_JSON_VALUE)
public class BurgerOrderRestController {

    private final BurgerOrderService burgerOrderService;

    @Autowired
    public BurgerOrderRestController(BurgerOrderService burgerOrderService) {
        this.burgerOrderService = burgerOrderService;
    }

    @GetMapping("/{orderid}")
    public ResponseEntity<BurgerOrder> getOrder(@PathVariable("orderid") String id) {
        return ResponseEntity.ok(burgerOrderService.getBurgerOrder(id));
    }

    @PostMapping("{/orderid}")
    public ResponseEntity<BurgerOrder> updateOrder(
            @PathVariable("orderid") String id,
            @RequestBody BurgerOrder burgerOrder) {

        return ResponseEntity.ok(burgerOrderService.updateBurgerOrder(id, burgerOrder));
    }
}