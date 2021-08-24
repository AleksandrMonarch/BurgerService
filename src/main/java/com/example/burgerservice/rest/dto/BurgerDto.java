package com.example.burgerservice.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class BurgerDto {

    private String id;

    private String name;

    private List<IngredientDto> ingredients;

//    @JsonProperty("order_id")
//    private BurgerOrderIdDto burgerOrder;
    @JsonProperty("order_id")
    private String burgerOrder;
}
