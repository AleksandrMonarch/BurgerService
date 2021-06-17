package com.example.burgerservice.mvc.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Burger {

    private String id;

    @NotBlank
    @Size(min = 5, max = 50, message = "name must be at least 5 characters long")
    private String name;
    private List<Ingredient> ingredients;
}
