package com.cdsofias.MojRozvrh.classroom;

import java.util.List;
import java.util.UUID;

public interface ClassroomService {
    List<Classroom> getClassrooms();
    Classroom getClassroom(UUID classroomId);
    Classroom addNewClassroom(CreateClassroomDto classroomDto);
    void deleteClassroom(UUID classroomId);
    Classroom updateClassroom(UUID classroomId, Classroom classroom);
}
