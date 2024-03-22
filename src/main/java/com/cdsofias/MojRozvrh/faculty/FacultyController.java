package com.cdsofias.MojRozvrh.faculty;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("faculty")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping
    public List<Faculty> getAllFaculties() {
        return facultyService.getAllFaculties();
    }

    @GetMapping("{id}")
    public Faculty getFacultyById(@PathVariable UUID id) {
        return facultyService.getFacultyById(id);
    }

    @PostMapping
    public Faculty createFaculty(@Valid @RequestBody CreateFacultyDto facultyDto) {
        return facultyService.createFaculty(facultyDto);
    }

    @PutMapping("{id}")
    public Faculty updateFaculty(@PathVariable UUID id, @RequestBody Faculty faculty) {
        return facultyService.updateFaculty(id, faculty);
    }

    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable UUID id) {
        facultyService.deleteFaculty(id);
    }
}
