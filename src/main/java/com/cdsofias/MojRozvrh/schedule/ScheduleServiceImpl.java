package com.cdsofias.MojRozvrh.schedule;

import com.cdsofias.MojRozvrh.users.User;
import com.cdsofias.MojRozvrh.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    public Schedule createSchedule(Schedule schedule) {
        if (schedule.getUser() != null && schedule.getUser().getId() != null) {
            User user = userRepository.findById(schedule.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            schedule.setUser(user);
        }
        return scheduleRepository.saveAndFlush(schedule);
    }
    @Override
    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule findScheduleById(UUID id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        return schedule.orElse(null);
    }

    @Override
    public Schedule deleteScheduleById(UUID id) {
        boolean exists = scheduleRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(
                    "Schedule with id " + id + " does not exist");
        }
        scheduleRepository.deleteById(id);
        return null;
    }

    @Override
    public Schedule updateScheduleById(UUID id, Schedule newSchedule) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Schedule with id " + id + " does not exist"));

        if (newSchedule.getName() != null && !newSchedule.getName().isEmpty() && !Objects.equals(schedule.getName(), newSchedule.getName())) {
            schedule.setName(newSchedule.getName());
        }

        if (newSchedule.getUser() != null) {
            User user = userRepository.findById(newSchedule.getUser().getId())
                    .orElseThrow(() -> new IllegalStateException(
                            "User with id " + newSchedule.getUser().getId() + " does not exist"));
            schedule.setUser(user);
        }

        return scheduleRepository.save(schedule);
    }
}

