/**
 * 
 */
package jp.co.axa.apidemo.services;

import java.util.List;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.CustomServiceException;

/**
 * @author Peter
 *
 */
public interface EmployeeService {

	public List<Employee> getEmployees() throws CustomServiceException;

	public Employee getEmployeeById(Long employeeId) throws CustomServiceException;

	public Employee saveOrUpdateEmployee(Employee employee) throws CustomServiceException;

	public Boolean deleteEmployeeById(Long employeeId) throws CustomServiceException;

	public Boolean deleteAllEmployee() throws CustomServiceException;

}
