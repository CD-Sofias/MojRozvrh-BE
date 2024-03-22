package com.cdsofias.MojRozvrh.subjects;

import java.util.List;
import java.util.UUID;


public interface SubjectService {
    Subject createSubject(CreateSubjectDto subjectDto);
    List<Subject> findAllSubjects();
    Subject findSubjectById(UUID id);
    Subject deleteSubjectById(UUID id);
    Subject updateSubjectById(UUID id, Subject newSubject);
}
