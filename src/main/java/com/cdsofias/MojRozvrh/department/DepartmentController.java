package com.cdsofias.MojRozvrh.department;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.getDepartments();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody CreateDepartmentDto departmentDto) {
        Department department = departmentService.addNewDepartment(departmentDto);
        return ResponseEntity.created(URI.create("/department/" + department.getId())).body(department);
    }

    @DeleteMapping("{departmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDepartment(@PathVariable("departmentId") UUID departmentId) {
        departmentService.deleteDepartment(departmentId);
    }

    @PutMapping("{departmentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Department updateDepartment(
            @PathVariable UUID departmentId,
            @RequestBody @Valid CreateDepartmentDto departmentDto) {
        return departmentService.updateDepartment(departmentId, departmentDto);
    }
}
