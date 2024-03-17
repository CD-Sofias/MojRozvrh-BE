package com.cdsofias.MojRozvrh.schedule;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/schedule")
@AllArgsConstructor
public class ScheduleController {
    private final ScheduleServiceImpl scheduleService;

    @PostMapping
    public Schedule createSchedule(@RequestBody Schedule schedule) {
        return scheduleService.createSchedule(schedule);
    }

    @GetMapping
    public List<Schedule> findAllSchedules() {
        return scheduleService.findAllSchedules();
    }

    @GetMapping("{id}")
    public Schedule findScheduleById(@PathVariable UUID id) {
        return scheduleService.findScheduleById(id);
    }

    @DeleteMapping("{id}")
    public Schedule deleteScheduleById(@PathVariable UUID id) {
        return scheduleService.deleteScheduleById(id);
    }

    @PutMapping("{id}")
    public Schedule updateScheduleById(@PathVariable UUID id, @RequestBody Schedule schedule) {
        return scheduleService.updateScheduleById(id, schedule);
    }
}
