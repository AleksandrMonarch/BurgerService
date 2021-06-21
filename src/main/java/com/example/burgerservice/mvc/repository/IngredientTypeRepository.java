package com.example.burgerservice.mvc.repository;

import com.example.burgerservice.mvc.domain.IngredientType;
import org.springframework.data.repository.CrudRepository;

public interface IngredientTypeRepository extends CrudRepository<IngredientType, String> {

}
