package com.cdsofias.MojRozvrh.groups;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record CreateGroupDto (
        @NotNull(message = "Field 'quantity' is required")
        @Min(value = 1, message = "Quantity must be min 1")
        @Max(value = 200, message = "Quantity must be max 200")
        Integer quantity,

        @NotNull(message = "Field 'name' is required")
        @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
        String name
) implements Serializable {}
