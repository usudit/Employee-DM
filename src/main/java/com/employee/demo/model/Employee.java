package com.employee.demo.model;

import jakarta.persistence.*;

@Entity
public class Employee {
	@Id
	private String empName;
	private String department;
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Employee [empName=" + empName + ", department=" + department + "]";
	}
	
	

}
