package jp.co.axa.apitest.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import jp.co.axa.apidemo.vo.EmployeeVo;

/**
 * @author Peter
 */
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerUnitTest {

	@Mock
	EmployeeService employeeService;

	@InjectMocks
	EmployeeController employeeController;
	Employee employee1;
	Employee employee2;
	Employee employee3;
	Employee employee4;
	Employee employee5;
	Employee employee6;

	@BeforeEach
	void setMockOutput() {

		employee1 = new Employee("Paul", 700000, "HR");
		employee2 = new Employee("Jenifer", 800000, "IT");
		employee3 = new Employee("Jessica", 1000000, "Robotic");
		employee4 = new Employee("Jason", 1000000, "AI");
		employee5 = new Employee("Chris", 900000, "Retail");
		employee6 = new Employee("Carmel", 900000, "Finance");

	}

	@Test
	public void testGetEmployees() {

		List<Employee> list = new ArrayList<>();
		list.addAll(Arrays.asList(employee1, employee2, employee3, employee4, employee5, employee6));
		when(employeeService.getEmployees()).thenReturn(list);

		ResponseEntity<List<EmployeeVo>> employeesList = employeeController.getEmployees();
		assertThat(employeesList.getBody().size()).isEqualTo(6);

	}

}
