package com.learntoyounus.service;

import com.learntoyounus.domain.EmployeeDto;
import com.learntoyounus.entity.EmployeeEntity;
import com.learntoyounus.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<EmployeeDto> getAllEmployee(int pageNumber, int pageSize, String sortOrder) {
        Sort sort = sortOrder.equals("asc") ? Sort.by("name").ascending() : Sort.by("name").descending();
        Pageable pagination = PageRequest.of(pageNumber, pageSize, sort);
        Page<EmployeeEntity> pageContent = employeeRepository.findAll(pagination);
        List<EmployeeEntity> employeeEntities = pageContent.getContent();
        List<EmployeeDto> employees = employeeEntities.stream()
                .map(employee -> new EmployeeDto(employee.getName(),
                        employee.getPhoneNumber(),employee.getDesignation()))
                .collect(Collectors.toList());

        return employees;
    }

    public EmployeeDto getEmployeeById(Long id) throws Exception{
        Optional<EmployeeEntity> employeeEntityOpt = employeeRepository.findById(id);
        EmployeeEntity employeeEntity = employeeEntityOpt.get();
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
