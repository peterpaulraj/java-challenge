package jp.co.axa.apidemo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Peter
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;

	@Column(name = "EMP_NAME")
	private String empName;

	@Column(name = "EMP_SALARY")
	private Integer empSalary;

	@Column(name = "EMP_DEPARTMENT")
	private String empDepartment;

	public Employee() {

	}

	/**
	 * Initial Data Insert
	 */
	public Employee(String empName, Integer empSalary, String empDepartment) {
		this.empName = empName;
		this.empSalary = empSalary;
		this.empDepartment = empDepartment;
	}

	/**
	 * @return the employeeId
	 */
	public Long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the empSalary
	 */
	public Integer getEmpSalary() {
		return empSalary;
	}

	/**
	 * @param empSalary the empSalary to set
	 */
	public void setEmpSalary(Integer empSalary) {
		this.empSalary = empSalary;
	}

	/**
	 * @return the empDepartment
	 */
	public String getEmpDepartment() {
		return empDepartment;
	}

	/**
	 * @param empDepartment the empDepartment to set
	 */
	public void setEmpDepartment(String empDepartment) {
		this.empDepartment = empDepartment;
	}

}
