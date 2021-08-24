package com.example.burgerservice.mvc.controller;

import com.example.burgerservice.mvc.domain.*;
import com.example.burgerservice.mvc.service.AddressService;
import com.example.burgerservice.mvc.service.IngredientService;
import com.example.burgerservice.mvc.service.OrderStatusService;
import com.example.burgerservice.mvc.service.impl.OrderServiceImpl;
import com.example.burgerservice.mvc.utils.service.DevService;
import com.example.burgerservice.mvc.utils.service.OperationHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes({"burgerOrder", "address"})
public class OrderController {

    private final OrderServiceImpl orderService;
    private final AddressService addressService;
    private final IngredientService ingredientService;
    private final OrderStatusService orderStatusService;
    private final OperationHistoryService operationHistoryService;
    private final DevService devService;

    @Autowired
    public OrderController(OrderServiceImpl orderService,
                           AddressService addressService,
                           IngredientService ingredientService,
                           OrderStatusService orderStatusService,
                           OperationHistoryService operationHistoryService,
                           DevService devService) {

        this.orderService = orderService;
        this.addressService = addressService;
        this.ingredientService = ingredientService;
        this.orderStatusService = orderStatusService;
        this.operationHistoryService = operationHistoryService;
        this.devService = devService;
    }

    @GetMapping("/newOrder")
    public String getOrderForm(
            @ModelAttribute BurgerOrder burgerOrder,
            @ModelAttribute Address address,
            Model model) {

        model.addAttribute("burgerOrder", burgerOrder);
        model.addAttribute("address", address);
        return "orderForm";
    }

    @PostMapping("/newOrder")
    public String processOrder(BurgerOrder burgerOrder, Address address, Errors error) {

        if (error.hasErrors()) {
            log.error("there are validation errors {}", error.getFieldErrors());
            return "orderForm";
        }

        OrderStatus createdStatus = orderStatusService.getOrderStatusById("CR");

        address = addressService.getEqualsAddressFromDBIfExists(address);
        burgerOrder.addAddress(address);
        burgerOrder.addOrderStatus(createdStatus);
        orderService.saveOrder(burgerOrder);
        log.info("save the order {}", burgerOrder);

        operationHistoryService.addRecord("save", "save burger order", burgerOrder);

        return "redirect:/orders/currentOrder";
    }

    @GetMapping("/updateOrder")
    public String makeChangesInOrder() {
        return "orderForm";
    }

    @PostMapping("/updateOrder")
    public String updateOrder(BurgerOrder burgerOrder, Address address) {

        burgerOrder.addAddress(address);
        orderService.saveOrder(burgerOrder);
        log.info("{} orders updated", burgerOrder);
        return "redirect:/orders/currentOrder";
    }

    //you try to make this works
    @GetMapping("/currentOrder")
    public String getBurgersByIngredients(
            @ModelAttribute BurgerOrder burgerOrder,
            Model model,
            IngredientListWrapper ingredientListWrapper) {

        model.addAttribute("burgerOrder", burgerOrder);
        model.addAttribute("burgers", burgerOrder.getBurgers());

        if (Objects.nonNull(ingredientListWrapper.getIngredients()) &&
                !ingredientListWrapper.getIngredients().isEmpty()) {

            model.addAttribute(
                    "burgers", getBurgersContainIngredients(burgerOrder,
                            ingredientListWrapper.getIngredients()));
        }
        model.addAttribute("ingredientListWrapper", new IngredientListWrapper());
        return "currentOrder";
    }

    @GetMapping("/getAllOrders")
    public String getAllOrders(Model model) {
        model.addAttribute("allOrders", orderService.getAllOrders());
        return "allOrders";
    }

    @ModelAttribute("address")
    private Address getAddress() {
        return new Address();
    }

    @ModelAttribute("ingredients")
    private List<Ingredient> getIngredients() {
        return ingredientService.getAllIngredients();
    }

    @PostConstruct
    private void saveAllOrdersStatuses() {
        List<OrderStatus> orderStatuses = Arrays.asList(
                new OrderStatus("CR", "Created"),
                new OrderStatus("IP", "In progress"),
                new OrderStatus("DN", "Done"),
                new OrderStatus("CL", "Cancelled")
        );

        orderStatusService.saveAllOrderStatus(orderStatuses);

        OrderStatus createdStatus = orderStatusService.getOrderStatusById("CR");

        List<BurgerOrder> burgerOrders = orderService.getAllOrders();
        burgerOrders.forEach(burgerOrder -> burgerOrder.addOrderStatus(createdStatus));
        burgerOrders.forEach(orderService::saveOrder);

        devService.checkProfile();
    }

    private List<Burger> getBurgersContainIngredients(BurgerOrder burgerOrder, List<Ingredient> ingredients) {
        List<Burger> filteredBurgers = new ArrayList<>();
        for (Burger burger : burgerOrder.getBurgers()) {
            if (burgerContainsIngredients(burger, ingredients)) {
                filteredBurgers.add(burger);
            }
        }
        return filteredBurgers;
    }

    private boolean burgerContainsIngredients(Burger burger, List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            if (!burgerContainsIngredient(burger, ingredient)) {
                return false;
            }
        }
        return true;
    }

    private boolean burgerContainsIngredient(Burger burger, Ingredient ingredient) {

        for (Ingredient burgerIngredient : burger.getIngredients()) {
            if (burgerIngredient.getId().equals(ingredient.getId())) {
                return true;
            }
        }
        return false;
    }
}

