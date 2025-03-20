package com.em.employeeservice.service;

import com.em.employeeservice.dto.EmployeeRequestDTO;
import com.em.employeeservice.dto.EmployeeResponseDTO;
import com.em.employeeservice.exception.EmailAlreadyExistsException;
import com.em.employeeservice.exception.EmployeeNotFoundException;
import com.em.employeeservice.mapper.EmployeeMapper;
import com.em.employeeservice.model.Employee;
import com.em.employeeservice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeResponseDTO> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeResponseDTO> employeeResponseDTOS = employees.stream().map(employee -> EmployeeMapper.toDto(employee)).toList();

        return employeeResponseDTOS;
    }

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        if (employeeRepository.existsByEmail(employeeRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A Employee with this email" + "already exists" + employeeRequestDTO.getEmail());
        }
        Employee newEmployee = employeeRepository.save(EmployeeMapper.toModel(employeeRequestDTO));

        return EmployeeMapper.toDto(newEmployee);
    }

    public EmployeeResponseDTO updateEmployee(UUID id, EmployeeRequestDTO employeeRequestDTO){


        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID" +  id));

        if (employeeRepository.existsByEmailAndIdNot(employeeRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A Employee with this email" + "already exists" + employeeRequestDTO.getEmail());
        }

        employee.setName(employeeRequestDTO.getName());
        employee.setAddress(employeeRequestDTO.getAddress());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setDateOfBirth(LocalDate.parse(employeeRequestDTO.getDateOfBirth()));

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toDto(updatedEmployee);

    }

    public void deleteEmployee(UUID id){
        employeeRepository.deleteById(id);
    }


}
