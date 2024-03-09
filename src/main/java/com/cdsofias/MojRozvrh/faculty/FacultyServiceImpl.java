package com.cdsofias.MojRozvrh.faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty getFacultyById(UUID id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
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
