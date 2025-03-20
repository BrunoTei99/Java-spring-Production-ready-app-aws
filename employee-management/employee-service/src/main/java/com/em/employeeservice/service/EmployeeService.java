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

        // Fetch the employee from the repository by ID, or throw an exception if not found
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));

        // Check if an employee with the new email already exists, excluding the current employee
        if (employeeRequestDTO.getEmail() != null &&
                !employeeRequestDTO.getEmail().equals(employee.getEmail()) &&
                employeeRepository.existsByEmailAndIdNot(employeeRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("An employee with this email already exists: " + employeeRequestDTO.getEmail());
        }

        // Only update the fields that are not null or empty in the request DTO
        if (employeeRequestDTO.getName() != null && !employeeRequestDTO.getName().isEmpty()) {
            employee.setName(employeeRequestDTO.getName());
        }

        if (employeeRequestDTO.getAddress() != null && !employeeRequestDTO.getAddress().isEmpty()) {
            employee.setAddress(employeeRequestDTO.getAddress());
        }

        if (employeeRequestDTO.getEmail() != null && !employeeRequestDTO.getEmail().isEmpty()) {
            employee.setEmail(employeeRequestDTO.getEmail());
        }

        if (employeeRequestDTO.getDateOfBirth() != null && !employeeRequestDTO.getDateOfBirth().isEmpty()) {
            employee.setDateOfBirth(LocalDate.parse(employeeRequestDTO.getDateOfBirth()));
        }

        // Save the updated employee
        Employee updatedEmployee = employeeRepository.save(employee);

        // Return the response DTO
        return EmployeeMapper.toDto(updatedEmployee);
    }

}
