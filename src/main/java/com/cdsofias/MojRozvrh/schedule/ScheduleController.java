package com.cdsofias.MojRozvrh.schedule;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shedule")
@AllArgsConstructor
public class ScheduleController {
    private final ScheduleServiceImpl sheduleService;

    @PostMapping
    public Schedule createShedule(@RequestBody Schedule schedule) {
        return sheduleService.createShedule(schedule);
    }

    @GetMapping
    public List<Schedule> findAllShedules() {
        return sheduleService.findAllShedules();
    }

    @GetMapping("{id}")
    public Schedule findSheduleById(@PathVariable UUID id) {
        return sheduleService.findSheduleById(id);
    }

    @DeleteMapping("{id}")
    public Schedule deleteSheduleById(@PathVariable UUID id) {
        return sheduleService.deleteSheduleById(id);
    }

    @PutMapping("{id}")
    public Schedule updateSheduleById(@PathVariable UUID id, @RequestBody Schedule schedule) {
        return sheduleService.updateSheduleById(id, schedule);
    }
}
