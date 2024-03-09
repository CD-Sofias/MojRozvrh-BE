package com.cdsofias.MojRozvrh.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(UUID id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(UUID id, Address address) {
        Address existingAddress = getAddressById(id);
        if (existingAddress != null) {
            existingAddress.setAddress(address.getAddress());
            return addressRepository.save(existingAddress);
        }
        return null;
    }

    @Override
    public void deleteAddress(UUID id) {
        addressRepository.deleteById(id);
    }
}
