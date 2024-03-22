package com.cdsofias.MojRozvrh.teachers;

import jakarta.validation.constraints.NotNull;

public record CreateTeacherDto(
        @NotNull
        String name,
        @NotNull
        String surname
) {
}
