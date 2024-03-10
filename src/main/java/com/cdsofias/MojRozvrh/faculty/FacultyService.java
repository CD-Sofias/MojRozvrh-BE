package com.cdsofias.MojRozvrh.faculty;

import java.util.List;
import java.util.UUID;

public interface FacultyService {
    List<Faculty> getAllFaculties();
    Faculty getFacultyById(UUID id);
    Faculty createFaculty(Faculty faculty);
    Faculty updateFaculty(UUID id, Faculty faculty);
    void deleteFaculty(UUID id);
}
