package com.em.employeeservice.service;

import com.em.employeeservice.dto.EmployeeResponseDTO;
import com.em.employeeservice.mapper.EmployeeMapper;
import com.em.employeeservice.model.Employee;
import com.em.employeeservice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeResponseDTO> getEmployees (){
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeResponseDTO> employeeResponseDTOS = employees.stream()
                .map(employee -> EmployeeMapper.toDto(employee)).toList();

        return employeeResponseDTOS;

    }
}
