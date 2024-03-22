package com.cdsofias.MojRozvrh.faculty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record CreateFacultyDto (
        @NotNull(message = "Field 'name' is required")
        @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
        String name
) implements Serializable {}
