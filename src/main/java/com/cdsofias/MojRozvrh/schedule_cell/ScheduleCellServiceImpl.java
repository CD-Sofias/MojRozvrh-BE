package com.cdsofias.MojRozvrh.schedule_cell;

import com.cdsofias.MojRozvrh.classroom.Classroom;
import com.cdsofias.MojRozvrh.classroom.ClassroomRepository;
import com.cdsofias.MojRozvrh.groups.Group;
import com.cdsofias.MojRozvrh.groups.GroupRepository;
import com.cdsofias.MojRozvrh.schedule.Schedule;
import com.cdsofias.MojRozvrh.schedule.ScheduleRepository;
import com.cdsofias.MojRozvrh.subjects.Subject;
import com.cdsofias.MojRozvrh.subjects.SubjectRepository;
import com.cdsofias.MojRozvrh.teachers.Teacher;
import com.cdsofias.MojRozvrh.teachers.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleCellServiceImpl implements ScheduleCellService {
    private final ScheduleCellRepository scheduleCellRepository;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final ClassroomRepository classroomRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleCell createScheduleCell(CreateScheduleCellDto scheduleCellDto) {
        Group group = groupRepository.findById(scheduleCellDto.groupId())
                .orElseThrow(() -> new IllegalStateException(
                        "Group with id " + scheduleCellDto.groupId() + " does not exist"));
        Subject subject = subjectRepository.findById(scheduleCellDto.subjectId())
                .orElseThrow(() -> new IllegalStateException(
                        "Subject with id " + scheduleCellDto.subjectId() + " does not exist"));
        Teacher teacher = teacherRepository.findById(scheduleCellDto.teacherId())
                .orElseThrow(() -> new IllegalStateException(
                        "Teacher with id " + scheduleCellDto.teacherId() + " does not exist"));
        Classroom classroom = classroomRepository.findById(scheduleCellDto.classroomId())
                .orElseThrow(() -> new IllegalStateException(
                        "Classroom with id " + scheduleCellDto.classroomId() + " does not exist"));
        Schedule schedule = scheduleRepository.findById(scheduleCellDto.scheduleId())
                .orElseThrow(() -> new IllegalStateException(
                        "Schedule with id " + scheduleCellDto.scheduleId() + " does not exist"));

        ScheduleCell scheduleCell = new ScheduleCell();
        scheduleCell.setGroup(group);
        scheduleCell.setSubject(subject);
        scheduleCell.setTeacher(teacher);
        scheduleCell.setClassroom(classroom);
        scheduleCell.setStartTime(scheduleCellDto.startTime());
        scheduleCell.setEndTime(scheduleCellDto.endTime());
        scheduleCell.setSchedule(schedule);

        return scheduleCellRepository.save(scheduleCell);
    }


    @Override
    public List<ScheduleCell> findAllScheduleCell() {
        return scheduleCellRepository.findAll();
    }

    @Override
    public ScheduleCell findScheduleCellById(UUID id) {
        return scheduleCellRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "ScheduleCell with id " + id + " does not exist"));
    }

    @Override
    public void deleteScheduleCellById(UUID id) {
        if (!scheduleCellRepository.existsById(id)) {
            throw new IllegalStateException(
                    "ScheduleCell with id " + id + " does not exist");
        }
        scheduleCellRepository.deleteById(id);
    }

    @Override
    public ScheduleCell updateScheduleCellById(UUID id, ScheduleCell newScheduleCell) {
        ScheduleCell scheduleCell = scheduleCellRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "ScheduleCell with id " + id + " does not exist"));

        if (newScheduleCell.getGroup() != null) {
            Group group = groupRepository.findById(newScheduleCell.getGroup().getId())
                    .orElseThrow(() -> new IllegalStateException(
                            "Group with id " + newScheduleCell.getGroup().getId() + " does not exist"));
            scheduleCell.setGroup(group);
        }

        if (newScheduleCell.getSubject() != null) {
            Subject subject = subjectRepository.findById(newScheduleCell.getSubject().getId())
                    .orElseThrow(() -> new IllegalStateException(
                            "Subject with id " + newScheduleCell.getSubject().getId() + " does not exist"));
            scheduleCell.setSubject(subject);
        }

        if (newScheduleCell.getTeacher() != null) {
            Teacher teacher = teacherRepository.findById(newScheduleCell.getTeacher().getId())
                    .orElseThrow(() -> new IllegalStateException(
                            "Teacher with id " + newScheduleCell.getTeacher().getId() + " does not exist"));
            scheduleCell.setTeacher(teacher);
        }

        if (newScheduleCell.getClassroom() != null) {
            Classroom classroom = classroomRepository.findById(newScheduleCell.getClassroom().getId())
                    .orElseThrow(() -> new IllegalStateException(
                            "Classroom with id " + newScheduleCell.getClassroom().getId() + " does not exist"));
            scheduleCell.setClassroom(classroom);
        }

        if (newScheduleCell.getStartTime() != null) {
            scheduleCell.setStartTime(newScheduleCell.getStartTime());
        }

        if (newScheduleCell.getEndTime() != null) {
            scheduleCell.setEndTime(newScheduleCell.getEndTime());
        }

        if (newScheduleCell.getSchedule() != null) {
            Schedule schedule = scheduleRepository.findById(newScheduleCell.getSchedule().getId())
                    .orElseThrow(() -> new IllegalStateException(
                            "Schedule with id " + newScheduleCell.getSchedule().getId() + " does not exist"));
            scheduleCell.setSchedule(schedule);
        }

        return scheduleCellRepository.save(scheduleCell);
    }


}
