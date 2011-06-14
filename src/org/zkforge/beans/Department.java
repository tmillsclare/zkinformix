package org.zkforge.beans;

public class Department {
	private String _id, _name = "";
	
	public Department() {
		//Default construct for QueryRunner
	}
	
	public Department(String id,
					  String name) {
		this._id = id;
		this._name = name;
	}
	
	public boolean isEmpty() {
		boolean ret = this._id.equals("") && this._name.equals("");
		
		return ret;
	}
	
	public void setId(String id) {
		this._id = id;
	}
	
	public String getId() {
		return _id;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getName() {
		return _name;
	}

	@Override
	public boolean equals(Object obj) {
		boolean equal = false;
		
		if(obj instanceof Department) {
			Department d = (Department)obj;
			equal = this._id.equals(d._id);
		}
		
		return equal;
	}

	@Override
	public int hashCode() {
		return this._id.hashCode();
	}
	
}
