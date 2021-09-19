package com.example.burgerservice.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class BaseResponseDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String response;

    public BaseResponseDto(String response) {
        this.response = response;
    }
}
