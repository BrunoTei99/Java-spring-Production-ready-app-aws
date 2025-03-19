package com.em.employeeservice.controller;

import com.em.employeeservice.dto.EmployeeResponseDTO;
import com.em.employeeservice.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees(){
        List<EmployeeResponseDTO> employees = employeeService.getEmployees();
        return ResponseEntity.ok().body(employees);
    }
}
