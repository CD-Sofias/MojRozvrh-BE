package com.cdsofias.MojRozvrh.subjects;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record CreateSubjectDto(
        @NotNull(message = "Field type is required")
        String type,
        @NotNull(message = "Field name is required")
        String name
) implements Serializable {
}
