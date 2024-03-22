package com.cdsofias.MojRozvrh.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.UUID;

public record CreateUserDto(
        @NotNull(message = "Field 'name' is required")
        @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
        String name,

        @NotNull(message = "Field 'surname' is required")
        @Size(min = 1, max = 100, message = "Surname must be between 1 and 100 characters")
        String surname,

        @NotNull(message = "Field 'email' is required")
        @Email(message = "Email should be valid")
        String email,

        @NotNull(message = "Field 'password' is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password,

        @NotNull(message = "Field 'role' is required")
        Role role,

        @NotNull(message = "Field 'departmentId' is required")
        UUID departmentId
) implements Serializable {
}
