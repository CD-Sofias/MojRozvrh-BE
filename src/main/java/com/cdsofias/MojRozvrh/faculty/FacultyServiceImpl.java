package com.cdsofias.MojRozvrh.faculty;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty getFacultyById(UUID id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty createFaculty(CreateFacultyDto facultyDto) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.name());
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty updateFaculty(UUID id, Faculty faculty) {
        Faculty existingFaculty = getFacultyById(id);
        if (existingFaculty != null) {
            existingFaculty.setName(faculty.getName());
            return facultyRepository.save(existingFaculty);
        }
        return null;
    }

    @Override
    public void deleteFaculty(UUID id) {
        facultyRepository.deleteById(id);
    }
}
