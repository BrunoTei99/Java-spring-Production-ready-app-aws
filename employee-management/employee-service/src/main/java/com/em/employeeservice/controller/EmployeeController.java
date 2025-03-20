package com.em.employeeservice.controller;

import com.em.employeeservice.dto.EmployeeRequestDTO;
import com.em.employeeservice.dto.EmployeeResponseDTO;
import com.em.employeeservice.dto.validators.CreateEmployeeValidationGroup;
import com.em.employeeservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee", description = "API for managing Employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @Operation(summary = "Get Employees")
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees() {
        List<EmployeeResponseDTO> employees = employeeService.getEmployees();
        return ResponseEntity.ok().body(employees);
    }

    @PostMapping
    @Operation(summary = "Create a new Employee")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @Validated({Default.class, CreateEmployeeValidationGroup.class}) @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.createEmployee(employeeRequestDTO);
        return ResponseEntity.ok().body(employeeResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an Employee")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable UUID id,@Validated({Default.class}) @RequestBody EmployeeRequestDTO employeeRequestDTO)
    {
        EmployeeResponseDTO employeeResponseDTO = employeeService.updateEmployee(id, employeeRequestDTO);
        return ResponseEntity.ok().body(employeeResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes an Employees")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
