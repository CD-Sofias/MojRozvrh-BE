package com.cdsofias.MojRozvrh.schedule;

import java.util.List;
import java.util.UUID;

public interface ScheduleService {
    Schedule createSchedule(CreateScheduleDto createScheduleDto);
    List<Schedule> findAllSchedules();
    Schedule findScheduleById(UUID id);
    Schedule deleteScheduleById(UUID id);
    Schedule updateScheduleById(UUID id, Schedule newSchedule);
}

