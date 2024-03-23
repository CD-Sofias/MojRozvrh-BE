package com.cdsofias.MojRozvrh.department;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    List<Department> getDepartments();

    Department addNewDepartment(CreateDepartmentDto department);
    void deleteDepartment(UUID departmentId);
    Department updateDepartment(UUID departmentId, CreateDepartmentDto departmentDto);
}

