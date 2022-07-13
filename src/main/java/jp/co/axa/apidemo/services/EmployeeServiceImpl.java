/**
 * 
 */
package jp.co.axa.apidemo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.CustomServiceException;
import jp.co.axa.apidemo.exception.ResourceNotFoundException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

/**
 * @author Peter
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	private static final String SERVER_ERROR = "Internal Server Error";
	private static final String DATA_NOT_FOUND = "Employee Data Not Found";

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	@Cacheable("employeeAllCache")
	public List<Employee> getEmployees() throws CustomServiceException {
		List<Employee> employeesList = new ArrayList<>();
		try {
			employeesList = employeeRepository.findAll();
			if (employeesList.isEmpty()) {
				logger.error(DATA_NOT_FOUND);
				throw new ResourceNotFoundException(DATA_NOT_FOUND);
			}

			if (logger.isInfoEnabled()) {
				logger.info(" Employee List Size :: {} ", employeesList.size());
			}

		} catch (Exception e) {
			logger.error(SERVER_ERROR);
			throw new CustomServiceException(SERVER_ERROR);

		}

		logger.info("List Size : {}", employeesList.size());

		return employeesList;

	}

	@Override
	@Cacheable(value = "employeeCache", key = "#employeeId")
	public Employee getEmployeeById(Long employeeId) throws CustomServiceException {
		Employee employee = new Employee();
		try {

			Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
			if (employeeOptional.isPresent()) {
				employee = employeeOptional.get();
			} else {
				logger.error(DATA_NOT_FOUND + " for this id  {} ", employeeId);
				throw new ResourceNotFoundException(DATA_NOT_FOUND + " for this id :: " + employeeId);
			}

		} catch (Exception e) {
			logger.error(SERVER_ERROR);
			throw new CustomServiceException(SERVER_ERROR);
		}

		logger.info("Generated Employee : {}", employee);
		return employee;
	}

	// @CachePut(value = "employeeCache")

	@Override

	@Caching(evict = { @CacheEvict(value = "employeeCache", allEntries = true),
			@CacheEvict(value = "employeeAllCache", allEntries = true) })
	public Employee saveOrUpdateEmployee(Employee employee) throws CustomServiceException {
		Employee employeeEntity = new Employee();
		try {

			employeeEntity = employeeRepository.save(employee);

		} catch (Exception e) {
			logger.error(SERVER_ERROR);
			throw new CustomServiceException(SERVER_ERROR);
		}
		return employeeEntity;
	}

	@Override
	@Caching(evict = { @CacheEvict(value = "employeeCache", key = "#employeeId", allEntries = true),
			@CacheEvict(value = "employeeAllCache", key = "#employeeId", allEntries = true) })
	public Boolean deleteEmployeeById(Long employeeId) throws CustomServiceException {
		Integer deleteEmployee = employeeRepository.deleteEmployeeById(employeeId);
		if (deleteEmployee > 0) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	@Caching(evict = { @CacheEvict(value = "employeeCache", allEntries = true),
			@CacheEvict(value = "employeeAllCache", allEntries = true) })
	public Boolean deleteAllEmployee() throws CustomServiceException {

		Integer deleteEmployee = employeeRepository.deleteAllEmployee();
		if (deleteEmployee > 0) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
