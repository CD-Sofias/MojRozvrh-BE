package com.cdsofias.MojRozvrh.users;

import com.cdsofias.MojRozvrh.department.Department;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.io.Serializable;

public record CreateUserDto(
        @NotBlank(message = "Name is required")
        @Size(max = 255, message = "Name must be less than 255 characters")
        String name,

        @NotBlank(message = "Surname is required")
        @Size(max = 255, message = "Surname must be less than 255 characters")
        String surname,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(max = 255, message = "Email must be less than 255 characters")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 255, message = "Password must be between 6 and 255 characters")
        String password,

        @NotNull(message = "Role is required")
        Role role,

        @NotNull(message = "Department is required")
        Department department
) implements Serializable {
}
