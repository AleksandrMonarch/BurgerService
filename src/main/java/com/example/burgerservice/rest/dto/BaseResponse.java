package com.example.burgerservice.rest.dto;

import lombok.Data;

@Data
public class BaseResponse {

    private String response;


    public BaseResponse(String response) {
        this.response = response;
    }
}
