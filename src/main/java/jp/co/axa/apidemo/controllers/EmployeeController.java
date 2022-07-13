/**
 * 
 */
package jp.co.axa.apidemo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.CustomServiceException;
import jp.co.axa.apidemo.services.EmployeeService;
import jp.co.axa.apidemo.util.CommonUtil;
import jp.co.axa.apidemo.vo.EmployeeVo;

/**
 * @author Peter
 *
 */
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * Get the All employee details
	 * 
	 * @return employeeList ResponseEntity
	 */
	@GetMapping("/employees")
	public ResponseEntity<List<EmployeeVo>> getEmployees() {
		List<EmployeeVo> empList = new ArrayList<>();
		try {
			List<Employee> employeesList = employeeService.getEmployees();
			if (employeesList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			CommonUtil.copyListProperties(employeesList, empList, EmployeeVo.class);
		} catch (CustomServiceException e) {
			return new ResponseEntity<>(empList, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(empList, HttpStatus.OK);
	}

	/**
	 * Get the employee information by employeeId
	 * 
	 * @param employeeId Long
	 * @return employeeVo ResponseEntity
	 */
	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<EmployeeVo> getEmployeeById(@PathVariable(value = "employeeId") Long employeeId) {
		EmployeeVo employeeVo = new EmployeeVo();
		try {
			Employee employee = employeeService.getEmployeeById(employeeId);
			if (employee == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			CommonUtil.copyProperties(employee, employeeVo);
		} catch (CustomServiceException e) {
			return new ResponseEntity<>(employeeVo, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(employeeVo, HttpStatus.OK);
	}

	@PostMapping("/employees")
	public ResponseEntity<EmployeeVo> createEmployee(@RequestBody EmployeeVo newEmployee) {
		EmployeeVo employeeVo = new EmployeeVo();
		Employee employee = new Employee();
		try {

			CommonUtil.copyProperties(newEmployee, employee);
			Employee savedEmployee = employeeService.saveOrUpdateEmployee(employee);
			if (savedEmployee == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			CommonUtil.copyProperties(savedEmployee, employeeVo);

		} catch (CustomServiceException exception) {
			return new ResponseEntity<>(employeeVo, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(employeeVo, HttpStatus.CREATED);
	}

	@DeleteMapping("/employees/{employeeId}")
	public ResponseEntity<Long> deleteEmployeeById(@PathVariable(value = "employeeId") Long employeeId) {
		try {
			Boolean isRemoved = employeeService.deleteEmployeeById(employeeId);
			if (Boolean.FALSE.equals(isRemoved)) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (CustomServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(employeeId, HttpStatus.OK);
	}

	@DeleteMapping("/employees")
	public ResponseEntity<HttpStatus> deleteAllEmployee() {
		try {
			Boolean isRemoved = employeeService.deleteAllEmployee();
			if (Boolean.FALSE.equals(isRemoved)) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (CustomServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/employees/{employeeId}")
	public ResponseEntity<EmployeeVo> updateEmployee(@PathVariable(value = "employeeId") Long employeeId,
			@Valid @RequestBody EmployeeVo updateEmployee) {

		EmployeeVo employeeVo = new EmployeeVo();
		try {

			Employee employee = employeeService.getEmployeeById(employeeId);
			if (employee == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			CommonUtil.copyProperties(updateEmployee, employee);

			Employee savedEmployee = employeeService.saveOrUpdateEmployee(employee);
			if (savedEmployee == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			CommonUtil.copyProperties(savedEmployee, employeeVo);

		} catch (CustomServiceException exception) {
			return new ResponseEntity<>(employeeVo, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(employeeVo, HttpStatus.OK);

	}

}
