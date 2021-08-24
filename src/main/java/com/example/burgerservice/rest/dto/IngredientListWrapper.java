package com.example.burgerservice.rest.dto;

import lombok.*;


import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientListWrapper {

    private List<IngredientDto> ingredients;
}
