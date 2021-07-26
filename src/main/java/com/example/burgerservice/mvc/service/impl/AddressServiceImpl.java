package com.example.burgerservice.mvc.service.impl;

import com.example.burgerservice.mvc.domain.Address;
import com.example.burgerservice.mvc.repository.AddressRepository;
import com.example.burgerservice.mvc.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public String findAddressIdByStreetAndCityAndStateAndZipNumber(Address address) {
        Address result = addressRepository
                .getAddressByStreetEqualsAndCityEqualsAndStateEqualsAndZipNumberEquals(address.getStreet(),
                        address.getCity(), address.getState(), address.getZipNumber());
        if (Objects.nonNull(result)) {
            return result.getId();
        }
        return null;
    }
}
