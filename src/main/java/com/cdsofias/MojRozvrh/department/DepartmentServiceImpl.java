package com.cdsofias.MojRozvrh.department;

import com.cdsofias.MojRozvrh.faculty.Faculty;
import com.cdsofias.MojRozvrh.faculty.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;

    @Override
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department addNewDepartment(Department department) {
        Optional<Department> departmentOptional = departmentRepository
                .findByName(department.getName());
        if (departmentOptional.isPresent()) {
            throw new IllegalStateException("Department already exists");
        }

        Faculty faculty = facultyRepository.findById(department.getFaculty().getId())
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        department.setFaculty(faculty);

        return departmentRepository.saveAndFlush(department);
    }

    @Override
    public void deleteDepartment(UUID departmentId) {
        boolean exists = departmentRepository.existsById(departmentId);
        if (!exists) {
            throw new IllegalStateException(
                    "Department with id " + departmentId + " does not exist");
        }
        departmentRepository.deleteById(departmentId);
    }

    public Department updateDepartment(UUID departmentId, String name, UUID facultyId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalStateException(
                        "Department with id " + departmentId + " does not exist"));

        if (name != null && !name.isEmpty() && !Objects.equals(department.getName(), name)) {
            department.setName(name);
        }

        if (facultyId != null) {
            Faculty faculty = facultyRepository.findById(facultyId)
                    .orElseThrow(() -> new IllegalStateException(
                            "Faculty with id " + facultyId + " does not exist"));
            department.setFaculty(faculty);
        }

        return departmentRepository.save(department);
    }
}
