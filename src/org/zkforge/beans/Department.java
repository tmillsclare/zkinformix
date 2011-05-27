package org.zkforge.beans;

public class Department {
	private String id, name = "";
	
	public Department() {
		//Default construct for QueryRunner
	}
	
	public Department(String id,
					  String name) {
		this.id = id;
		this.name = name;
	}
	
	public boolean isEmpty() {
		boolean ret = this.id.equals("") && this.name.equals("");
		
		return ret;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
}
