package com.em.employeeservice.mapper;

import com.em.employeeservice.dto.EmployeeResponseDTO;
import com.em.employeeservice.model.Employee;

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
}
