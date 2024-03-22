package com.cdsofias.MojRozvrh.schedule_cell;

import java.util.List;
import java.util.UUID;

public interface ScheduleCellService {
    ScheduleCell createScheduleCell(CreateScheduleCellDto scheduleCellDto);
    List<ScheduleCell> findAllScheduleCell();
    ScheduleCell findScheduleCellById(UUID id);
    void deleteScheduleCellById(UUID id);
    ScheduleCell updateScheduleCellById(UUID id, ScheduleCell newScheduleCell);
}
