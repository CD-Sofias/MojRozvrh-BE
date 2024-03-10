package com.cdsofias.MojRozvrh.address;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    List<Address> getAllAddress();
    Address getAddressById(UUID id);
    Address createAddress(Address address);
    Address updateAddress(UUID id, Address address);
    void deleteAddress(UUID id);
}
