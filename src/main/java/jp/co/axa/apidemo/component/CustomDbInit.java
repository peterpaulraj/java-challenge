package jp.co.axa.apidemo.component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.entities.Role;
import jp.co.axa.apidemo.entities.RolesEnum;
import jp.co.axa.apidemo.entities.User;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.repositories.RoleRepository;
import jp.co.axa.apidemo.repositories.UserRepository;

/**
 * User, Roles, and employee details database Initialization. This class will be
 * executed before starting the application.
 * 
 * @author Peter
 */
@Component
public class CustomDbInit {

	private static final String PASSWORD = "password";

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@PostConstruct
	private void postConstruct() {

		// Add Role
		List<Role> roleList = new ArrayList<>();

		roleList.add(new Role(RolesEnum.ROLE_ADMIN));
		roleList.add(new Role(RolesEnum.ROLE_USER));
		roleRepository.saveAll(roleList);

		// Create New User
		// Admin
		Set<Role> roles = new HashSet<>();
		Role adminRole = roleRepository.findByName(RolesEnum.ROLE_ADMIN);
		roles.add(adminRole);
		userRepository.save(new User("Peter", "Peter@gmail.com", encoder.encode(PASSWORD), roles));

		// User
		List<User> usersList = new ArrayList<>();
		roles = new HashSet<>();
		Role userRole = roleRepository.findByName(RolesEnum.ROLE_USER);
		roles.add(userRole);
		usersList.add(new User("Paul", "paul@gmail.com", encoder.encode(PASSWORD), roles));
		usersList.add(new User("Jenifer", "jeni@gmail.com", encoder.encode(PASSWORD), roles));
		usersList.add(new User("Jessica", "jessi@gmail.com", encoder.encode(PASSWORD), roles));
		usersList.add(new User("Jason", "jsaon@gmail.com", encoder.encode(PASSWORD), roles));
		usersList.add(new User("Chris", "chris@gmail.com", encoder.encode(PASSWORD), roles));
		usersList.add(new User("Carmel", "carmel@gmail.com", encoder.encode(PASSWORD), roles));
		userRepository.saveAll(usersList);

		// Add Employee information
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee("Peter", 1000000, "Admin"));
		empList.add(new Employee("Paul", 700000, "HR"));
		empList.add(new Employee("Jenifer", 800000, "IT"));
		empList.add(new Employee("Jessica", 1000000, "Robotic"));
		empList.add(new Employee("Jason", 1000000, "AI"));
		empList.add(new Employee("Chris", 900000, "Retail"));
		empList.add(new Employee("Carmel", 900000, "Finance"));
		employeeRepository.saveAll(empList);

	}

}
