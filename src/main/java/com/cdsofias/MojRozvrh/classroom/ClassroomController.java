package com.cdsofias.MojRozvrh.classroom;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("classroom")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomService classroomService;

    @GetMapping
    public List<Classroom> getClassrooms() {
        return classroomService.getClassrooms();
    }

    @GetMapping("/{classroomId}")
    public Classroom getClassroom(@PathVariable UUID classroomId) {
        return classroomService.getClassroom(classroomId);
    }

    @PostMapping
    public Classroom registerNewClassroom(@RequestBody Classroom classroom) {
        return classroomService.addNewClassroom(classroom);
    }

    @DeleteMapping("{classroomId}")
    public void deleteClassroom(@PathVariable("classroomId") UUID classroomId) {
        classroomService.deleteClassroom(classroomId);
    }

    @PutMapping("/{classroomId}")
    public Classroom updateClassroom(
            @PathVariable UUID classroomId,
            @RequestBody Classroom classroom) {
        return classroomService.updateClassroom(classroomId, classroom);
    }
}