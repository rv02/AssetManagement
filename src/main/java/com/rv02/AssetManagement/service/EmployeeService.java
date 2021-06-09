package com.rv02.AssetManagement.service;

import com.rv02.AssetManagement.dao.EmployeeRepository;
import com.rv02.AssetManagement.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> getEmployee(int id) {
        return employeeRepository.findById(id);
    }
}
