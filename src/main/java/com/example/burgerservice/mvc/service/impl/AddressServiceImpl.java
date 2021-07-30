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
    public Address getEqualsAddressFromBDIfExists(Address newAddress) {

        Address oldAddress = addressRepository
                 .getAddressByStreetEqualsAndCityEqualsAndStateEqualsAndZipNumberEquals(newAddress.getStreet(),
                         newAddress.getCity(), newAddress.getState(), newAddress.getZipNumber());
        if (Objects.nonNull(oldAddress)) {
            return oldAddress;
        }
        return newAddress;
    }
}
