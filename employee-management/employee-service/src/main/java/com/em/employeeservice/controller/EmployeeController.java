package com.em.employeeservice.controller;

import com.em.employeeservice.dto.EmployeeRequestDTO;
import com.em.employeeservice.dto.EmployeeResponseDTO;
import com.em.employeeservice.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees() {
        List<EmployeeResponseDTO> employees = employeeService.getEmployees();
        return ResponseEntity.ok().body(employees);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.createEmployee(employeeRequestDTO);
        return ResponseEntity.ok().body(employeeResponseDTO);
    }
}
