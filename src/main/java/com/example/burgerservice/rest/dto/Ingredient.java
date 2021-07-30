package com.example.burgerservice.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Ingredient {

    @NotBlank
    private String id;

    private String name;

    @JsonProperty("typeId")
    private String type;
}
