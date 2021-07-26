package com.example.burgerservice.mvc.service;

import com.example.burgerservice.mvc.domain.Address;

public interface AddressService {

    String findAddressIdByStreetAndCityAndStateAndZipNumber(Address address);
}
