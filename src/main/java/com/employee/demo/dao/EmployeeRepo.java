package com.employee.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.employee.demo.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
//public interface EmployeeRepo extends CrudRepository<Employee, String> {

}
