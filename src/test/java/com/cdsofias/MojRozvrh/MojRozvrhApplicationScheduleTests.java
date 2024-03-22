package com.cdsofias.MojRozvrh;

import com.cdsofias.MojRozvrh.schedule.CreateScheduleDto;
import com.cdsofias.MojRozvrh.schedule.Schedule;
import com.cdsofias.MojRozvrh.schedule.ScheduleRepository;
import com.cdsofias.MojRozvrh.schedule.ScheduleServiceImpl;
import com.cdsofias.MojRozvrh.users.User;
import com.cdsofias.MojRozvrh.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MojRozvrhApplicationScheduleTests {

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateSchedule() {
        UUID userId = UUID.randomUUID();
        CreateScheduleDto createScheduleDto = new CreateScheduleDto("Test Schedule", userId);

        User user = new User();
        user.setId(userId);

        Schedule schedule = new Schedule();
        schedule.setName("Test Schedule");
        schedule.setUser(user);

        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));
        when(scheduleRepository.saveAndFlush(any(Schedule.class))).thenReturn(schedule);

        Schedule createdSchedule = scheduleService.createSchedule(createScheduleDto);

        assertEquals(createScheduleDto.name(), createdSchedule.getName());
        assertEquals(createScheduleDto.userId(), createdSchedule.userId());
    }



    @Test
    public void testFindAllSchedules() {
        Schedule schedule1 = new Schedule();
        schedule1.setName("Test Schedule 1");
        User user1 = new User();
        user1.setId(UUID.randomUUID());
        schedule1.setUser(user1);

        Schedule schedule2 = new Schedule();
        schedule2.setName("Test Schedule 2");
        User user2 = new User();
        user2.setId(UUID.randomUUID());
        schedule2.setUser(user2);

        List<Schedule> schedules = Arrays.asList(schedule1, schedule2);

        when(scheduleRepository.findAll()).thenReturn(schedules);

        List<Schedule> returnedSchedules = scheduleService.findAllSchedules();

        assertEquals(2, returnedSchedules.size());
        assertEquals(schedule1.getName(), returnedSchedules.get(0).getName());
        assertEquals(schedule2.getName(), returnedSchedules.get(1).getName());
    }

    @Test
    public void testFindScheduleById() {
        Schedule schedule = new Schedule();
        schedule.setName("Test Schedule");
        User user = new User();
        user.setId(UUID.randomUUID());
        schedule.setUser(user);

        UUID scheduleId = UUID.randomUUID();
        schedule.setId(scheduleId);

        when(scheduleRepository.findById(any(UUID.class))).thenReturn(Optional.of(schedule));

        Schedule returnedSchedule = scheduleService.findScheduleById(scheduleId);

        assertEquals(schedule.getId(), returnedSchedule.getId());
        assertEquals(schedule.getName(), returnedSchedule.getName());
        assertEquals(schedule.getUser().getId(), returnedSchedule.getUser().getId());
    }

    @Test
    public void testDeleteScheduleById() {
        UUID scheduleId = UUID.randomUUID();

        when(scheduleRepository.existsById(any(UUID.class))).thenReturn(true);

        scheduleService.deleteScheduleById(scheduleId);

        verify(scheduleRepository, times(1)).deleteById(scheduleId);
    }

    @Test
    public void testDeleteScheduleByIdThrowsException() {
        UUID scheduleId = UUID.randomUUID();

        when(scheduleRepository.existsById(any(UUID.class))).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> scheduleService.deleteScheduleById(scheduleId));

        verify(scheduleRepository, times(0)).deleteById(any(UUID.class));
    }

    @Test
    public void testUpdateScheduleById() {
        UUID scheduleId = UUID.randomUUID();
        Schedule existingSchedule = new Schedule();
        existingSchedule.setId(scheduleId);
        existingSchedule.setName("Test Schedule");
        User user = new User();
        user.setId(UUID.randomUUID());
        existingSchedule.setUser(user);

        Schedule newSchedule = new Schedule();
        newSchedule.setName("New Test Schedule");
        User newUser = new User();
        newUser.setId(UUID.randomUUID());
        newSchedule.setUser(newUser);

        when(scheduleRepository.findById(any(UUID.class))).thenReturn(Optional.of(existingSchedule));
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(newUser));
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(newSchedule);

        Schedule updatedSchedule = scheduleService.updateScheduleById(scheduleId, newSchedule);

        assertEquals(newSchedule.getName(), updatedSchedule.getName());
        assertEquals(newSchedule.getUser().getId(), updatedSchedule.getUser().getId());
    }


}
