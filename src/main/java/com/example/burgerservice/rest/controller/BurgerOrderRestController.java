package com.example.burgerservice.rest.controller;

import com.example.burgerservice.rest.dto.BaseResponse;
import com.example.burgerservice.rest.dto.BurgerOrder;
import com.example.burgerservice.rest.service.BurgerOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/api/order",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class BurgerOrderRestController {

    private final BurgerOrderService burgerOrderService;

    @Autowired
    public BurgerOrderRestController(BurgerOrderService burgerOrderService) {
        this.burgerOrderService = burgerOrderService;
    }

    @GetMapping("/{orderid}")
    public ResponseEntity<BurgerOrder> getOrder(@PathVariable("orderid") String id) {
        log.info("response GET method by url /api/order/{}", id);
        return ResponseEntity.ok(burgerOrderService.getBurgerOrder(id));
    }

    @PutMapping("/{orderid}")
    public ResponseEntity<BurgerOrder> updateOrder(
            @PathVariable("orderid") String id,
            @RequestBody BurgerOrder burgerOrder) {
        log.info("response POST method by url /api/order/{} and {} into Request Body", id, burgerOrder);
        return ResponseEntity.ok(burgerOrderService.updateBurgerOrder(id, burgerOrder));
    }

    @DeleteMapping("/{orderid}")
    public ResponseEntity<BaseResponse> deleteOrder(@PathVariable("orderid") String id) {
        burgerOrderService.deleteBurgerOrderById(id);
        return ResponseEntity.ok(new BaseResponse(String.format("deleted order by id with id = %s", id)));
    }
}