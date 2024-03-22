package com.cdsofias.MojRozvrh.schedule_cell;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public record CreateScheduleCellDto (
        @NotNull(message = "Field 'groupId' is required")
        UUID groupId,

        @NotNull(message = "Field 'subjectId' is required")
        UUID subjectId,

        @NotNull(message = "Field 'teacherId' is required")
        UUID teacherId,

        @NotNull(message = "Field 'classroomId' is required")
                UUID classroomId,

        @NotNull(message = "Field 'startTime' is required")
        Date startTime,

        @NotNull(message = "Field 'endTime' is required")
        Date endTime,

        @NotNull(message = "Field 'scheduleId' is required")
        UUID scheduleId
) implements Serializable {}


