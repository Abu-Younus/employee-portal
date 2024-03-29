package com.learntoyounus.service;

import com.learntoyounus.domain.EmployeeDto;
import com.learntoyounus.entity.EmployeeEntity;
import com.learntoyounus.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(employeeDto.getName());
        employeeEntity.setPhoneNumber(employeeDto.getPhoneNumber());
        employeeEntity.setDesignation(employeeDto.getDesignation());
        employeeRepository.save(employeeEntity);

        return employeeDto;
    }

    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<EmployeeDto> employees = employeeEntities.stream()
                .map(employee -> new EmployeeDto(employee.getName(),
                        employee.getPhoneNumber(),employee.getDesignation()))
                .collect(Collectors.toList());

        return employees;
    }

    public EmployeeDto getEmployeeById(Long id) throws Exception{
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(employeeEntity.getName());
        employeeDto.setPhoneNumber(employeeEntity.getPhoneNumber());
        employeeDto.setDesignation(employeeEntity.getDesignation());

        return employeeDto;
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        employeeEntity.setName(employeeDto.getName());
        employeeEntity.setPhoneNumber(employeeDto.getPhoneNumber());
        employeeEntity.setDesignation(employeeDto.getDesignation());
        employeeRepository.save(employeeEntity);

        return employeeDto;
    }

    public boolean deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        employeeRepository.delete(employeeEntity);
        return true;
    }
}
