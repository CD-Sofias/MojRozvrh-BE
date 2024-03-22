package com.cdsofias.MojRozvrh.teachers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("teacher")
@AllArgsConstructor
public class TeacherController {
    private final TeacherServiceImpl teacherService;

    @PostMapping
    public Teacher createTeacher(@Valid @RequestBody CreateTeacherDto teacher) {
        return teacherService.createTeacher(teacher);
    }

    @GetMapping
    public List<Teacher> findAllTeachers() {
        return teacherService.findAllTeachers();
    }

    @GetMapping("{id}")
    public Teacher findTeacherById(@PathVariable UUID id) {
        return teacherService.findTeacherById(id);
    }

    @DeleteMapping("{id}")
    public Teacher deleteTeacherById(@PathVariable UUID id) {
        return teacherService.deleteTeacherById(id);
    }

    @PutMapping("{id}")
    public Teacher updateTeacherById(@PathVariable UUID id, @RequestBody Teacher teacher) {
        return teacherService.updateTeacherById(id, teacher);
    }
}
