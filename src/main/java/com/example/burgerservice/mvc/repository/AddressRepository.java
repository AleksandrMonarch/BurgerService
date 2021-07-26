package com.example.burgerservice.mvc.repository;

import com.example.burgerservice.mvc.domain.Address;


import org.springframework.data.repository.CrudRepository;


public interface AddressRepository extends CrudRepository<Address, String> {

    Address getAddressByStreetEqualsAndCityEqualsAndStateEqualsAndZipNumberEquals(String street, String city,
                                                                               String state, String zupNumber);
}
