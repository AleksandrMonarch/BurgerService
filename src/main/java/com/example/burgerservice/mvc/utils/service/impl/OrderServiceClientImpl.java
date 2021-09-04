package com.example.burgerservice.mvc.utils.service.impl;

import com.example.burgerservice.rest.dto.*;
import com.example.burgerservice.mvc.utils.service.OrderServiceClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceClientImpl implements OrderServiceClient {

    @Override
    public IngredientListWrapper getAllIngredients() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/ingredient/all";
        return restTemplate.getForObject(url, IngredientListWrapper.class);
    }

    @Override
    public void saveIngredient(IngredientDto ingredientDto) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/ingredient";
        HttpEntity<IngredientDto> request = new HttpEntity<>(ingredientDto);
        restTemplate.postForObject(url, request, IngredientDto.class);
    }

    @Override
    public IngredientTypeDto getIngredientType(String id, String param) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/ingredienttype/" + id + "?param=" + param;
        return restTemplate.getForObject(url, IngredientTypeDto.class);
    }

    @Override
    public void saveIngredientType(IngredientTypeDto ingredientTypeDto) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/ingredienttype/";
        HttpEntity<IngredientTypeDto> request = new HttpEntity<>(ingredientTypeDto);
        restTemplate.postForObject(url, request, IngredientDto.class);
    }

    @Override
    public IngredientTypeListWrapper getAllIngredientTypes() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/ingredienttype/all";
        return restTemplate.getForObject(url, IngredientTypeListWrapper.class);
    }

    // TODO: 28.08.2021 fix the problem 
    @Override
    public BurgerOrderDto updateOrder(String id, BurgerOrderDto burgerOrderDto) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/order/" + id;
        HttpEntity<BurgerOrderDto> request = new HttpEntity<>(burgerOrderDto);
        return restTemplate.exchange(url, HttpMethod.PUT, request, BurgerOrderDto.class).getBody();
    }

    @Override
    public BurgerOrderDto getOrder(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/order/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(headers);
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, BurgerOrderDto.class).getBody();
    }

    RequestCallback requestCallback(final  BurgerOrderDto burgerOrderDto) {
        return clientHttpRequest -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(clientHttpRequest.getBody(), burgerOrderDto);
            clientHttpRequest.getHeaders().add(
                    HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        };
    }
}