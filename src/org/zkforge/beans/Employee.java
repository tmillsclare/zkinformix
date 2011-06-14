package org.zkforge.beans;

public class Employee {
	
	private int _age = 0;
	private String _id, _firstName, _lastName = "";
	private Department _department = null;
	
	public Employee() {
		//default constructor for Employee
	}
	
	public Employee(String id, 
					String firstName, 
					String lastName, 
					int age, 
					Department department)
	{
		this._id = id;
		this._firstName = firstName;
		this._lastName = lastName;
		this._age = age;
		this._department = department;
	}
	
	public void setId(String id) {
		this._id = id;
	}

	public String getId() {
		return _id;
	}
	
	public void setFirstName(String first_name) {
		this._firstName = first_name;
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setLastName(String last_name) {
		this._lastName = last_name;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setAge(int age) {
		this._age = age;
	}

	public int getAge() {
		return _age;
	}
	
	public void setDepartment(Department department) {
		this._department = department;
	}

	public Department getDepartment() {
		return _department;
	}
}
