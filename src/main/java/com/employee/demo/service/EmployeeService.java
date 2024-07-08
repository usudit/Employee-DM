package com.employee.demo.service;

import java.util.List;
import java.util.Optional;

import com.employee.demo.model.Employee;

public interface EmployeeService {
	
	Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(long id);
    Employee updateEmployee(Employee employee,long id);
    void deleteEmployee(long id);

}
