package com.cdsofias.MojRozvrh.users;

import com.cdsofias.MojRozvrh.department.Department;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record CreateUserDto(
        @NotNull(message = "Field name is required")
        String name,
        @NotNull(message = "Field surname is required")
        String surname,
        @NotNull(message = "Field surname is required")
        String email,
        @NotNull(message = "Field surname is required")
        String password,
        @NotNull(message = "Field surname is required")
        Role role,
        @NotNull(message = "Field surname is required")
        Department department
) implements Serializable {
}
