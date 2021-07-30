package com.example.burgerservice.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {

    @JsonIgnore
    private String id;

    private String street;

    private String city;

    private String state;

    @JsonProperty("zip_number")
    private String zipNumber;
}
