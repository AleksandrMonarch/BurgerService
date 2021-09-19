package com.example.burgerservice.rest.dto;

import lombok.Getter;

@Getter
public class DataResponseDto<T> extends BaseResponseDto {

    private T data;

    public DataResponseDto(T data) {
        super(null);
        this.data = data;
    }
}
