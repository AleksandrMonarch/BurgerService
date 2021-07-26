package com.example.burgerservice.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Ingredient {

    private String id;

    private String name;

    @JsonProperty("typeId")
    private String type;
}
