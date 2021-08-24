package com.example.burgerservice.mvc.utils.service.impl;

import com.example.burgerservice.rest.dto.IngredientListWrapper;
import com.example.burgerservice.mvc.utils.service.OrderServiceClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceClientImpl implements OrderServiceClient {

    @Override
    public IngredientListWrapper getAllIngredients() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/api/ingredient/all";
        return restTemplate.getForObject(url, IngredientListWrapper.class);
    }
}
