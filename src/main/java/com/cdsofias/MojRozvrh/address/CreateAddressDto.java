package com.cdsofias.MojRozvrh.address;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record CreateAddressDto (
    @NotNull(message = "Field 'address' is required")
    @Size(min = 3, max = 100, message = "Address must be between 3 and 100 characters")
    String address
) implements Serializable {}