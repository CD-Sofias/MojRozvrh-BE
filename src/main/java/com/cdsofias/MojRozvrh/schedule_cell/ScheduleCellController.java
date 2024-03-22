package com.cdsofias.MojRozvrh.schedule_cell;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("schedule_cell")
@AllArgsConstructor
public class ScheduleCellController {
    private final ScheduleCellServiceImpl scheduleCellServiceImpl;

    @PostMapping
    public ResponseEntity<ScheduleCell> createScheduleCell(@Valid @RequestBody CreateScheduleCellDto scheduleCellDto) {
        ScheduleCell scheduleCell = scheduleCellServiceImpl.createScheduleCell(scheduleCellDto);
        return ResponseEntity.created(URI.create("/scheduleCell/" + scheduleCell.getId())).body(scheduleCell);
    }

    @GetMapping
    public List<ScheduleCell> findAllScheduleCell() {
        return scheduleCellServiceImpl.findAllScheduleCell();
    }

    @GetMapping("{id}")
    public ScheduleCell findScheduleCellById(@PathVariable UUID id) {
        return scheduleCellServiceImpl.findScheduleCellById(id);
    }

    @DeleteMapping("{id}")
    public void deleteScheduleCellById(@PathVariable UUID id) {
         scheduleCellServiceImpl.deleteScheduleCellById(id);
    }

    @PutMapping("{id}")
    public ScheduleCell updateScheduleCellById(@PathVariable UUID id, @RequestBody ScheduleCell scheduleCell) {
        return scheduleCellServiceImpl.updateScheduleCellById(id, scheduleCell);
    }
}
