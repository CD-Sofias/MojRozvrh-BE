package com.cdsofias.MojRozvrh.auth.dto;

import java.io.Serializable;

public record LoginResponse(
        String token,
        String message
) implements Serializable {
}
