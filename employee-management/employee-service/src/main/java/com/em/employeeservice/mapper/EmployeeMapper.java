package com.em.employeeservice.mapper;

import com.em.employeeservice.dto.EmployeeRequestDTO;

import com.em.employeeservice.dto.EmployeeResponseDTO;
import com.em.employeeservice.model.Employee;

import java.time.LocalDate;

public class EmployeeMapper {
    public static EmployeeResponseDTO toDto(Employee employee){
        EmployeeResponseDTO employeeDTO = new EmployeeResponseDTO();
        employeeDTO.setId(employee.getId().toString());
        employeeDTO.setName(employee.getName());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setDateOfBirth(employee.getDateOfBirth().toString());

        return employeeDTO;

    }

    public static Employee toModel(EmployeeRequestDTO employeeRequestDto){
        Employee employee  = new Employee();
        employee.setName(employeeRequestDto.getName());
        employee.setAddress(employeeRequestDto.getAddress());
        employee.setEmail(employeeRequestDto.getEmail());
        employee.setDateOfBirth(LocalDate.parse(employeeRequestDto.getDateOfBirth()));
        employee.setRegistereDate(LocalDate.parse(employeeRequestDto.getRegisteredDate()));

        return employee;

    }
}
