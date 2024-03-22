package com.cdsofias.MojRozvrh.department;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.UUID;

public record CreateDepartmentDto(
        @NotNull(message = "Field name is required")
        @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
        String name,
        @NotNull(message = "Field facultyId is required")
        UUID facultyId
) implements Serializable {}
