package com.cdsofias.MojRozvrh.classroom;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.UUID;

public record CreateClassroomDto (
    @NotNull(message = "Field 'type' is required")
    @Size(min = 1, max = 100, message = "Type must be between 1 and 100 characters")
    String type,

    @NotNull(message = "Field 'capacity' is required")
    Integer capacity,

    @NotNull(message = "Field 'classroomNumber' is required")
    Integer classroomNumber,

    @NotNull(message = "Field 'addressId' is required")
    UUID addressId
) implements Serializable {}
