package com.example.burgerservice.security;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findUserByUserName(String userName);
}
