package com.cdsofias.MojRozvrh.auth.dto;

import java.io.Serializable;

public record AuthResponse(String token, String message) implements Serializable {
}
