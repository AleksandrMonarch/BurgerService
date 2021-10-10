package com.example.burgerservice.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataRequestDto<T> {

    private T data;
}
