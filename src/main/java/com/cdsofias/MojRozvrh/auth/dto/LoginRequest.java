package com.cdsofias.MojRozvrh.auth.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record LoginRequest(
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "Password is required")
        String password
) implements Serializable {
}
