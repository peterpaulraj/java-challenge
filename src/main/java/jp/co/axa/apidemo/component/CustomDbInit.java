package jp.co.axa.apidemo.component;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

/**
 * @author Peter
 */
@Component
public class CustomDbInit {

	private List<Employee> empList = new ArrayList<>();

	@Autowired
	private EmployeeRepository employeeRepository;

	@PostConstruct
	private void postConstruct() {
		empList.add(new Employee("Paul", 700000, "HR"));
		empList.add(new Employee("Jenifer", 800000, "IT"));
		empList.add(new Employee("Jessica", 1000000, "Robotic"));
		empList.add(new Employee("Jason", 1000000, "AI"));
		empList.add(new Employee("Chris", 900000, "Retail"));
		empList.add(new Employee("Carmel", 900000, "Finance"));

		employeeRepository.saveAll(empList);

	}

}
