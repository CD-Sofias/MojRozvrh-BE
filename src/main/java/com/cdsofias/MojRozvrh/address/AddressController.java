package com.cdsofias.MojRozvrh.address;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public List<Address> getAllAddress() {
        return addressService.getAllAddress();
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable UUID id) {
        return addressService.getAddressById(id);
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@Valid @RequestBody CreateAddressDto addressDto) {
        Address address = addressService.createAddress(addressDto);
        return ResponseEntity.created(URI.create("/address/" + address.getId())).body(address);
    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable UUID id, @RequestBody Address address) {
        return addressService.updateAddress(id, address);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable UUID id) {
        addressService.deleteAddress(id);
    }
}
