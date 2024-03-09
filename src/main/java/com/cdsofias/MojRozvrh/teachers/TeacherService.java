package com.cdsofias.MojRozvrh.teachers;

import java.util.List;
import java.util.UUID;

public interface TeacherService {
    Teacher createTeacher(Teacher subject);
    List<Teacher> findAllTeachers();
    Teacher findTeacherById(UUID id);
    Teacher deleteTeacherById(UUID id);
    Teacher updateTeacherById(UUID id, Teacher newSubject);
}
