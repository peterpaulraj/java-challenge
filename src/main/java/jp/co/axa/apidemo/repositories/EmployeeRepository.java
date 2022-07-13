package jp.co.axa.apidemo.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.axa.apidemo.entities.Employee;

/**
 * @author Peter
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Transactional
	@Modifying
	@Query("DELETE FROM Employee WHERE employeeId = :employeeId")
	public Integer deleteEmployeeById(Long employeeId);

	@Transactional
	@Modifying
	@Query("DELETE FROM Employee")
	public Integer deleteAllEmployee();

}
