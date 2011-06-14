package org.zkforge.beans;

public class Employee {
	
	private int age = 0;
	private String id, firstName, lastName = "";
	private Department department = null;
	
	public Employee() {
		//default constructor for Employee
	}
	
	public Employee(String id, 
					String firstName, 
					String lastName, 
					int age, 
					Department department)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.department = department;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String last_name) {
		this.lastName = last_name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getDepartment() {
		return department;
	}
}
