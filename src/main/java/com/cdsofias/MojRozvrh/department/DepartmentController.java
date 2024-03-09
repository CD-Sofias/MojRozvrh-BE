package com.cdsofias.MojRozvrh.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @PostMapping
    public Department registerNewDepartment(@RequestBody Department department) {
        return departmentService.addNewDepartment(department);
    }

    @DeleteMapping(path = "{departmentId}")
    public void deleteDepartment(@PathVariable("departmentId") UUID departmentId) {
        departmentService.deleteDepartment(departmentId);
    }

    @PutMapping("/{departmentId}")
    public Department updateDepartment(
            @PathVariable UUID departmentId,
            @RequestBody Department department) {
        return departmentService.updateDepartment(departmentId, department.getName(), department.getFaculty().getId());
    }
}
