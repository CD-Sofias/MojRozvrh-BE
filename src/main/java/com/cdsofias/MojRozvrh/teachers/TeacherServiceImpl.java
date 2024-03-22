package com.cdsofias.MojRozvrh.teachers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public Teacher createTeacher(CreateTeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherDto.name());
        teacher.setSurname(teacherDto.surname());
        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findTeacherById(UUID id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        return teacher.orElse(null);
    }

    @Override
    public Teacher deleteTeacherById(UUID id) {
        teacherRepository.deleteById(id);
        return null;
    }

    @Override
    public Teacher updateTeacherById(UUID id, Teacher newTeacher) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if(optionalTeacher.isPresent()){
            Teacher teacher = optionalTeacher.get();
            teacher.setSurname(newTeacher.getSurname());
            teacher.setName(newTeacher.getName());
            return teacherRepository.save(teacher);
        }
        return null;
    }
}
