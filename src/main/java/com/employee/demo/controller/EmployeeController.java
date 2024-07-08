package com.employee.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.demo.dao.EmployeeRepo;
import com.employee.demo.model.Employee;
import com.employee.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
		
	}
	
	
	@PostMapping("/addEmployee")
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
		//TODO: process POST request
		employeeService.saveEmployee(employee);
		logger.info("this is Employee object added "+ employee);
		return new ResponseEntity<>("employee obj added for employee: "+employee.getEmpName() ,HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllEmployee")
	//public ResponseEntity<List<Employee>> getAllEmployee(@RequestParam String id)
	public ResponseEntity<List<Employee>> getAllEmployee() {
		//Employee employee = employeeRepo.findById(id).orElse(new Employee());
		List<Employee> employee = employeeService.getAllEmployees();
		logger.info("this is employee list retrieved "+ employee);
		return new ResponseEntity<>(employee ,HttpStatus.OK);
		/*List<Employee> employees = new ArrayList<>();
		employeeRepo.findAll().forEach(employees::add);
	    //return employees;
	    return new ResponseEntity<>(employees ,HttpStatus.OK);*/
	}
	
	@GetMapping("/getEmployee")
	public ResponseEntity<Employee> getEmployee(@RequestParam String id) {
	//public ResponseEntity<List<Employee>> getAllEmployee() {
		Employee employee = employeeService.getEmployeeById(Long.valueOf(id)).orElse(new Employee());
		//List<Employee> employee = employeeRepo.findAll();
		logger.info("this is employee object retrieved "+ employee);
		return new ResponseEntity<>(employee ,HttpStatus.OK);
	}
	
	@PutMapping("/updateEmployee")
	public ResponseEntity<Employee> updateEmployee(@RequestParam String id, @RequestBody Employee employee) {
		//public ResponseEntity<List<Employee>> getAllEmployee() {
			Employee employeeToUpdate = employeeService.updateEmployee(employee, Long.valueOf(id));
			//List<Employee> employee = employeeRepo.findAll();
			logger.info("this is employee object updated "+ employeeToUpdate);
			return new ResponseEntity<>(employeeToUpdate ,HttpStatus.OK);
		}
	
	@DeleteMapping("/deleteEmployee")
	public ResponseEntity<String> deleteEmployee(@RequestParam String id) {
		//public ResponseEntity<List<Employee>> getAllEmployee() {
			employeeService.deleteEmployee(Long.valueOf(id));
			//List<Employee> employee = employeeRepo.findAll();
			logger.info("Employee with id deleted "+ id);
			//return new ResponseEntity<>(employee ,HttpStatus.OK);
			return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
		}
	
	

}
