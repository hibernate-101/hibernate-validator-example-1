/*This example shows annotation based validation.
 * Here we have created a simple employee class and added validation 
some validation using hibernate annotation */
package com.validator.hibernate.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Email;

public class Employee {
	@Min(value = 1, groups = EmpIdCheck.class)
	private int empId;
	@NotNull(message = "Employee Name can't be null")
	@Size(min = 5, max = 20, message = "Name should contain at least 5 chracters")
	private String empName;
	@Email
	private String empEmail;
	@CreditCardNumber
	private String empCreditCardNumber;

	public Employee() {
		super();

	}

	public Employee(int empId, String empName, String empEmail,
			String empCreditCardNumber) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empEmail = empEmail;
		this.empCreditCardNumber = empCreditCardNumber;
	}

}
