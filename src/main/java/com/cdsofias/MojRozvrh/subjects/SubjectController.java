package com.cdsofias.MojRozvrh.subjects;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/subject")
@AllArgsConstructor
public class SubjectController {
    private final SubjectServiceImpl subjectService;

    @PostMapping
    public Subjects createSubject(@RequestBody Subjects subject) {
        return subjectService.createSubject(subject);
    }

    @GetMapping
    public List<Subjects> findAllSubjects() {
        return subjectService.findAllSubjects();
    }

    @GetMapping("{id}")
    public Subjects findSubjectById(@PathVariable UUID id) {
        return subjectService.findSubjectById(id);
    }

    @DeleteMapping("{id}")
    public Subjects deleteSubjectById(@PathVariable UUID id) {
        return subjectService.deleteSubjectById(id);
    }

    @PutMapping("{id}")
    public Subjects updateSubjectById(@PathVariable UUID id, @RequestBody Subjects subject) {
        return subjectService.updateSubjectById(id, subject);
    }
}

