/**
 * 
 */
package jp.co.axa.apidemo.vo;

/**
 * Employee value object (DTO)
 * 
 * @author Peter
 */
public class EmployeeVo extends CommonVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8818608430284275963L;
	private Long employeeId;
	private String empName;
	private Integer empSalary;
	private String empDepartment;

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
