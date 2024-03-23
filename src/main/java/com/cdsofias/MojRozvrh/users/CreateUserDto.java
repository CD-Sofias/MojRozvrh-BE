package com.cdsofias.MojRozvrh.users;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.UUID;

public record CreateUserDto(
        @NotBlank
        String username,
        @NotBlank
        String email,
        @NotBlank
        String password,

        @NotBlank
        Role role,
        UUID departmentId
) implements Serializable {
}
