package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.Address;
import com.example.burgerservice.mvc.domain.BurgerOrder;
import com.example.burgerservice.mvc.service.AddressService;
import com.example.burgerservice.mvc.service.impl.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes({"burgerOrder", "address"})
public class OrderController {

    private final OrderServiceImpl orderService;
    private final AddressService addressService;

    @Autowired
    public OrderController(OrderServiceImpl orderService, AddressService addressService) {
        this.orderService = orderService;
        this.addressService = addressService;
    }

    @GetMapping("/newOrder")
    public String getOrderForm() {
        return "orderForm";
    }

    @PostMapping("/newOrder")
    public String processOrder(@Valid BurgerOrder burgerOrder, @Valid Address address, Errors error,
                               Model model) {

        if (error.hasErrors()) {
            log.error("there are validation errors {}", error.getFieldErrors());
            return "orderForm";
        }

        address.setId(addressService.findAddressIdByStreetAndCityAndStateAndZipNumber(address));

        burgerOrder.addAddress(address);
        orderService.saveOrder(burgerOrder);
        log.info("save the order {}", burgerOrder);
        model.addAttribute("burgers", burgerOrder.getBurgers());
        return "currentOrder";
    }

    @GetMapping("/updateOrder")
    public String makeChangesInOrder() {
        return "orderForm";
    }

    @PostMapping("/updateOrder")
    public String updateOrder(@Valid BurgerOrder burgerOrder, @Valid Address address) {

        address.setId(addressService.findAddressIdByStreetAndCityAndStateAndZipNumber(address));
        burgerOrder.addAddress(address);
        orderService.saveOrder(burgerOrder);
        log.info("{} orders updated", burgerOrder);
        return "currentOrder";
    }

    @GetMapping("/getAllOrders")
    public String getAllOrders(Model model) {
        model.addAttribute("allOrders", orderService.getAllOrders());
        return "allOrders";
    }

    @ModelAttribute("address")
    public Address getAddress() {
        return new Address();
    }

}
