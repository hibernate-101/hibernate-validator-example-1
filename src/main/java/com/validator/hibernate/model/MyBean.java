//This example shows property level constraint validation.
/*Here we apply constraints on the getter methods, we should not apply it on setter method.
 * Also we should avoid applying constraints on both fields and getter method because then it will be validated twice.*/
package com.validator.hibernate.model;

import javax.validation.constraints.NotNull;

public class MyBean {

	private String empName;

	@NotNull(message = "Employee Name shouldn't be null")
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

}
