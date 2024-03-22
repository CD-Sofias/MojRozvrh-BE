package com.cdsofias.MojRozvrh.schedule;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("schedule")
@AllArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@Valid @RequestBody CreateScheduleDto createScheduleDto) {
        Schedule schedule = scheduleService.createSchedule(createScheduleDto);
        return ResponseEntity.created(URI.create("/schedule/" + schedule.getId())).body(schedule);
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
