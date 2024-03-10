package com.cdsofias.MojRozvrh.schedule;

import java.util.List;
import java.util.UUID;

public interface ScheduleService {
    Schedule createShedule(Schedule schedule);
    List<Schedule> findAllShedules();
    Schedule findSheduleById(UUID id);
    Schedule deleteSheduleById(UUID id);
    Schedule updateSheduleById(UUID id, Schedule newSchedule);
}
