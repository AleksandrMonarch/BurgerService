package com.example.burgerservice.rest.service.impl;

import com.example.burgerservice.mvc.repository.BurgerRepository;
import com.example.burgerservice.rest.dto.Burger;
import com.example.burgerservice.rest.mapper.BurgerMapper;
import com.example.burgerservice.rest.service.BurgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class BurgerServiceImpl implements BurgerService {

    private final BurgerRepository burgerRepository;
    private final BurgerMapper burgerMapper;

    @Autowired
    public BurgerServiceImpl(BurgerRepository burgerRepository, BurgerMapper burgerMapper) {
        this.burgerRepository = burgerRepository;
        this.burgerMapper = burgerMapper;
    }

    @Override
    public Burger getBurgerById(String id) {
        return burgerMapper.burgerDao2Dto(
                burgerRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new));
    }
}
