/*This examples shows validation using inheritance.If we validate the object of child class
then automatically parent class validation will occur. 
 */
package com.validator.hibernate.model;

import javax.validation.constraints.NotNull;

public class MyChildBean extends MyBean {
	private String data;

	@NotNull
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
