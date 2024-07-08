package com.employee.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.employee.demo.dao.EmployeeRepo;
import com.employee.demo.model.Employee;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceTest {
	
	@Mock
    private EmployeeRepo employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;


    @BeforeEach
    public void setup(){
    	
    	employee = new Employee();	
    	employee.setId(111L);
        employee.setEmpName("Jones");
        employee.setDepartment("Admin");
    }

    @Test
    @Order(1)
    public void saveEmployeeTest(){
        // precondition
        given(employeeRepository.save(employee)).willReturn(employee);

        //action
        Employee savedEmployee = employeeService.saveEmployee(employee);

        // verify the output
        System.out.println(savedEmployee);
        assertThat(savedEmployee).isNotNull();
    }

    @Test
    @Order(2)
    public void getEmployeeById(){
        // precondition
        given(employeeRepository.findById(111L)).willReturn(Optional.of(employee));

        // action
        Employee existingEmployee = employeeService.getEmployeeById(employee.getId()).get();

        // verify
        System.out.println(existingEmployee);
        assertThat(existingEmployee).isNotNull();

    }


    @Test
    @Order(3)
    public void getAllEmployee(){
        Employee employee1 = new Employee();
        employee.setId(111L);
        employee.setEmpName("Jones");
        employee.setDepartment("Admin");

        // precondition
        given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));

        // action
        List<Employee> employeeList = employeeService.getAllEmployees();

        // verify
        System.out.println(employeeList);
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isGreaterThan(1);
    }

    @Test
    @Order(4)
    public void updateEmployee(){

        // precondition
        given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));
        employee.setId(111L);
        employee.setEmpName("Jones");
        employee.setDepartment("Admin");
        given(employeeRepository.save(employee)).willReturn(employee);

        // action
        Employee updatedEmployee = employeeService.updateEmployee(employee,employee.getId());

        // verify
        System.out.println(updatedEmployee);
        assertThat(updatedEmployee.getEmpName()).isEqualTo("Jones");
        assertThat(updatedEmployee.getDepartment()).isEqualTo("Admin");
    }

    @Test
    @Order(5)
    public void deleteEmployee(){

        // precondition
        willDoNothing().given(employeeRepository).deleteById(employee.getId());

        // action
        employeeService.deleteEmployee(employee.getId());

        // verify
        verify(employeeRepository, times(1)).deleteById(employee.getId());
    }


	
}
