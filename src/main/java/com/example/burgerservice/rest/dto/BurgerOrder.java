package com.example.burgerservice.rest.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BurgerOrder {

    private String id;

    private String orderName;

    private LocalDateTime createdAt;

    @JsonUnwrapped
    private Address address;

    private List<Burger> burgers;
}
