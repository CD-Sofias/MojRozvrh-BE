package com.cdsofias.MojRozvrh.department;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody CreateDepartmentDto departmentDto) {
        Department department = departmentService.addNewDepartment(departmentDto);
        return ResponseEntity.created(URI.create("/department/" + department.getId())).body(department);
    }

    @DeleteMapping("{departmentId}")
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
