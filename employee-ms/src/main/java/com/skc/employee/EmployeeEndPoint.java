/**
 * 
 */
package com.skc.employee;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sitakant
 *
 */
@RestController
public class EmployeeEndPoint {
	
	@GetMapping("/employee")
	public Employee getSampleEmployee(){
		return new Employee("Sitakant", new Random().nextInt(), UUID.randomUUID().toString());
	}
	
}

class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer empId;
	private String deptName;
	
	/**
	 * @param name
	 * @param empId
	 * @param dept
	 */
	public Employee(String name, Integer empId, String dept) {
		super();
		this.name = name;
		this.empId = empId;
		this.deptName = dept;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the empId
	 */
	public Integer getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	};
	
	
	
}
