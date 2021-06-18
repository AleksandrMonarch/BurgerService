package com.example.burgerservice.service;

import com.example.burgerservice.mvc.domain.Burger;
import com.example.burgerservice.mvc.domain.Ingredient;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BurgerHistoryService {

    private static long maxId = 0;

    private static final List<Operation> operations = new ArrayList<>();

    private static class Operation {
        private final String id;
        private final Burger burger;
        private final Date createdDate;

        public Operation(Burger burger) {
            this.id = String.valueOf(++maxId);
            burger.setId("BRG" + id);
            this.burger = burger;
            createdDate = new Date();
        }

        @Override
        public String toString() {
            return "Operation{" +
                    "burger=" + burger +
                    ", createdDate=" + createdDate +
                    '}';
        }
    }

    public static void saveBurger(Burger burger) {
        Operation operation = new Operation(burger);
        operations.add(operation);
    }

    public static List<Operation> getBurgersCountFromDate(Date date) {
        return operations.stream()
                .filter(operation -> operation.createdDate.after(date))
                .collect(Collectors.toList());
    }

    public static List<Operation> getOperations() {
        return new ArrayList<>(operations);
    }

    public static List<Burger> getBurgerByIngredient(String ingredientId) {
            List<Burger> result = new ArrayList<>();
            for (Operation operation : operations) {
                for (Ingredient ingredient : operation.burger.getIngredients()) {
                    if (ingredient.getId().equals(ingredientId)) {
                        result.add(operation.burger);
                    }
                }
            }
            return result;
    }
 }
