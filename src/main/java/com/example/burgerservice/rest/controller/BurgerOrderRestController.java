package com.example.burgerservice.rest.controller;

import com.example.burgerservice.mvc.utils.service.OrderServiceClient;
import com.example.burgerservice.rest.dto.BaseResponseDto;
import com.example.burgerservice.rest.dto.BurgerOrderDto;
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
    private final OrderServiceClient orderServiceClient;

    @Autowired
    public BurgerOrderRestController(BurgerOrderService burgerOrderService, OrderServiceClient orderServiceClient) {
        this.burgerOrderService = burgerOrderService;
        this.orderServiceClient = orderServiceClient;
    }

    @GetMapping("/{orderid}")
    public ResponseEntity<BurgerOrderDto> getOrder(@PathVariable("orderid") String id) {
        log.info("response GET method by url /api/order/{}", id);
        return ResponseEntity.ok(burgerOrderService.getBurgerOrder(id));
    }

    @PutMapping("/{orderid}")
    public ResponseEntity<BurgerOrderDto> updateOrder(
            @PathVariable("orderid") String id,
            @RequestBody BurgerOrderDto burgerOrderDto) {
        log.info("response PUT method by url /api/order/{} and {} into Request Body", id, burgerOrderDto);
        return ResponseEntity.ok(burgerOrderService.updateBurgerOrder(id, burgerOrderDto));
    }

    @DeleteMapping("/{orderid}")
    public ResponseEntity<BaseResponseDto> deleteOrder(@PathVariable("orderid") String id) {
        burgerOrderService.deleteBurgerOrderById(id);
        return ResponseEntity.ok(new BaseResponseDto(String.format("deleted order by id with id = %s", id)));
    }

    @PutMapping("/cancel/{orderid}")
    public ResponseEntity<BurgerOrderDto> cancelOrder(@PathVariable("orderid") String id) {
        return ResponseEntity.ok(burgerOrderService.cancelOrder(id));
    }

    @PutMapping("/resttemplate/{orderid}")
    public BurgerOrderDto updateOrderByRestTemplate(@PathVariable("orderid") String id,
                                          @RequestBody BurgerOrderDto burgerOrderDto) {
        return orderServiceClient.updateOrder(id, burgerOrderDto);
    }

    @GetMapping("/resttemplate/{orderid}")
    public ResponseEntity<BurgerOrderDto> getOrderByRestTemplate(@PathVariable("orderid") String id) {
        return ResponseEntity.ok(orderServiceClient.getOrder(id));
    }
}