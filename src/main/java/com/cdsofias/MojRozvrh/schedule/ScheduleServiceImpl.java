package com.cdsofias.MojRozvrh.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule createShedule(Schedule schedule) {
        schedule.setId(UUID.randomUUID());
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> findAllShedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule findSheduleById(UUID id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        return schedule.orElse(null);
    }

    @Override
    public Schedule deleteSheduleById(UUID id) {
        scheduleRepository.deleteById(id);
        return null;
    }

    @Override
    public Schedule updateSheduleById(UUID id, Schedule newSchedule) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        if(optionalSchedule.isPresent()){
            Schedule schedule = optionalSchedule.get();
            schedule.setName(newSchedule.getName());
            if (newSchedule.getUser() != null) {
                schedule.setUser(newSchedule.getUser());
            }
            return scheduleRepository.save(schedule);
        }
        return null;
    }
}
