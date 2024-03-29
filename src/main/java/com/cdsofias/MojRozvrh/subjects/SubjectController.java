package com.cdsofias.MojRozvrh.subjects;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping
    public Subject createSubject(@RequestBody CreateSubjectDto subjectDto) {
        return subjectService.createSubject(subjectDto);
    }

    @GetMapping
    public List<Subject> findAllSubjects() {
        return subjectService.findAllSubjects();
    }

    @GetMapping("{id}")
    public Subject findSubjectById(@PathVariable UUID id) {
        return subjectService.findSubjectById(id);
    }

    @DeleteMapping("{id}")
    public Subject deleteSubjectById(@PathVariable UUID id) {
        return subjectService.deleteSubjectById(id);
    }

    @PutMapping("{id}")
    public Subject updateSubjectById(@PathVariable UUID id, @RequestBody Subject subject) {
        return subjectService.updateSubjectById(id, subject);
    }
}

