package com.employee.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.demo.controller.EmployeeController;
import com.employee.demo.dao.EmployeeRepo;
import com.employee.demo.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	
	
	Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	EmployeeRepo employeeRepo;
	
	
	
	public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}


	@Override
	public Employee saveEmployee(Employee employee) {
		
		logger.info("this is inside saveEmployee Call in EmployeeServiceImpl");
		
		return employeeRepo.save(employee);
	}


	@Override
	public List<Employee> getAllEmployees() {
		
		logger.info("this is inside getAllEmployees Call in EmployeeServiceImpl");
		return employeeRepo.findAll();
	}


	@Override
	public Optional<Employee> getEmployeeById(long id) {
		//return Optional.empty();
		logger.info("this is inside getEmployeeById Call in EmployeeServiceImpl");
		return employeeRepo.findById(id);
	}


	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		logger.info("this is inside updateEmployee Call in EmployeeServiceImpl");
		
		
		Employee existingEmployee = employeeRepo.findById(id)
                .orElseThrow(()->new RuntimeException());
		
		existingEmployee.setEmpName(employee.getEmpName());
		existingEmployee.setDepartment(employee.getDepartment());

     
        employeeRepo.save(existingEmployee);
        return existingEmployee;
        
	}


	@Override
	public void deleteEmployee(long id) {
		
		logger.info("this is inside deleteEmployee Call in EmployeeServiceImpl");
		
		employeeRepo.deleteById(id);
		
	}

}
