package com.cdsofias.MojRozvrh.subjects;

import java.util.List;
import java.util.UUID;


public interface SubjectService {
    Subjects createSubject(Subjects subject);
    List<Subjects> findAllSubjects();
    Subjects findSubjectById(UUID id);
    Subjects deleteSubjectById(UUID id);
    Subjects updateSubjectById(UUID id, Subjects newSubject);
}
