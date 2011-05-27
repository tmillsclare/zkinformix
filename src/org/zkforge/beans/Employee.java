package org.zkforge.beans;

public class Employee {
	
	private int age = 0;
	private String id, firstName, lastName, departmentId = "";
	
	public Employee() {
		//default constructor for Employee
	}
	
	public Employee(String id, 
					String firstName, 
					String lastName, 
					int age, 
					String departmentId)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.departmentId = departmentId;
	}
	
	public static Employee empty() {
		return new Employee("", "", "", 0, "");
	}
	
	public boolean isEmpty() {
		boolean ret = this.id.equals("") && this.firstName.equals("") &&
					  this.lastName.equals("") && (this.age == 0) && this.departmentId.equals("");
		
		return ret;
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


	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}


	public String getDepartmentId() {
		return departmentId;
	}
}
