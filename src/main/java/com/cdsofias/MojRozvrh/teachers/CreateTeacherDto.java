package com.cdsofias.MojRozvrh.teachers;

import jakarta.validation.constraints.NotNull;

public record CreateTeacherDto(
        @NotNull(message = "Field name is required")
        String name,
        @NotNull(message = "Field surname is required")
        String surname
) {
}
