//This project illustrates basic example of hibernate validator.
package com.validator.hibernate.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import com.validator.hibernate.model.EmpIdCheck;
import com.validator.hibernate.model.Employee;
import com.validator.hibernate.model.EmployeeXMLValidation;
import com.validator.hibernate.model.ExampleBean;
import com.validator.hibernate.model.MethodParamValidation;
import com.validator.hibernate.model.MyChildBean;

public class ValidatorTest {
	public static void main(String[] args) throws FileNotFoundException,
			NoSuchMethodException, SecurityException {
		// Here we are getting Validator instance using ValidFactory.
		ValidatorFactory validatorFactory = Validation
				.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		System.out.println("\nSimple field level validation");
		Employee emp = new Employee(-1, "RK", "Rk123@gmail.", "12345");
		Set<ConstraintViolation<Employee>> validationErrors = validator
				.validate(emp);

		if (!validationErrors.isEmpty()) {
			for (ConstraintViolation<Employee> error : validationErrors) {
				System.out.println(error.getMessageTemplate() + "::"
						+ error.getPropertyPath() + "::" + error.getMessage());

			}
		}
		System.out.println("\nXML based validation example");
		Configuration<?> config = Validation.byDefaultProvider().configure();
		config.addMapping(new FileInputStream("employeeXMLValidation.xml"));
		ValidatorFactory validationFactory_1 = config.buildValidatorFactory();
		Validator validator_1 = validationFactory_1.getValidator();
		System.out
				.println("Simple field level validation using xml configuration file");
		EmployeeXMLValidation emp_1 = new EmployeeXMLValidation(-1, "Rose",
				"Rs@gmail.com", "12345");
		Set<ConstraintViolation<EmployeeXMLValidation>> validationErrors1 = validator_1
				.validate(emp_1);

		if (!validationErrors1.isEmpty()) {
			for (ConstraintViolation<EmployeeXMLValidation> error : validationErrors1) {
				System.out.println(error.getMessageTemplate() + "::"
						+ error.getInvalidValue() + "::"
						+ error.getPropertyPath() + "::" + error.getMessage());

			}
		}
		System.out.println("\nValidation Group example");
		validationErrors = validator.validate(emp, EmpIdCheck.class);

		if (!validationErrors.isEmpty()) {
			for (ConstraintViolation<Employee> error : validationErrors) {
				System.out.println(error.getMessageTemplate() + "::"
						+ error.getPropertyPath() + "::" + error.getMessage());

			}
		}
		System.out.println("\nValidation with inheritance example");

		// Validation inheritance and Property-level constraints example
		MyChildBean childBean = new MyChildBean();
		Set<ConstraintViolation<MyChildBean>> validationInheritanceErrors = validator
				.validate(childBean);

		if (!validationInheritanceErrors.isEmpty()) {
			for (ConstraintViolation<MyChildBean> error : validationInheritanceErrors) {
				System.out.println(error.getMessageTemplate() + "::"
						+ error.getPropertyPath() + "::" + error.getMessage());

			}
		}
		System.out
				.println("\nValidation in composition using @Valid annotation");

		// @Valid annotation - validation in composition example
		ExampleBean compositionBean = new ExampleBean();
		compositionBean.setMyChildBean(new MyChildBean());
		Set<ConstraintViolation<ExampleBean>> validationCompositionErrors = validator
				.validate(compositionBean);

		if (!validationCompositionErrors.isEmpty()) {
			for (ConstraintViolation<ExampleBean> error : validationCompositionErrors) {
				System.out.println(error.getMessageTemplate() + "::"
						+ error.getPropertyPath() + "::" + error.getMessage());

			}
		}
		System.out.println("\nParameter validation example");
		MethodParamValidation MethodParamValidation = new MethodParamValidation(
				"Arman");
		Method method = MethodParamValidation.class.getMethod("displayData",
				String.class);
		Object[] params = { "Singer" };
		ExecutableValidator executableValidator = validator.forExecutables();
		Set<ConstraintViolation<MethodParamValidation>> violations = executableValidator
				.validateParameters(MethodParamValidation, method, params);
		if (!violations.isEmpty()) {
			for (ConstraintViolation<MethodParamValidation> error : violations) {
				System.out.println(error.getMessageTemplate() + "::"
						+ error.getPropertyPath() + "::" + error.getMessage());

			}
		}
	}
}
