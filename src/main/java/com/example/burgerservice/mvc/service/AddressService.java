package com.example.burgerservice.mvc.service;

import com.example.burgerservice.mvc.domain.Address;

public interface AddressService {

    Address getEqualsAddressFromBDIfExists(Address newAddress);
}
