package com.example.burgerservice.repositoty.impl;

import com.example.burgerservice.mvc.domain.Ingredient;
import com.example.burgerservice.repositoty.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

@Repository
//@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IngredientRepositoryImpl implements IngredientRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IngredientRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        return Optional.empty();
    }
}
