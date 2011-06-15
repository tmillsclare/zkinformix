package org.zkforge.dao.sql;

import org.zkforge.beans.Department;
import org.zkforge.dao.DatabaseInformation;

public class DepartmentQuerySet implements QuerySet<Department> {
	
	private final String _selectAllDepartments = "SELECT * FROM %1s;";
	private final String _selectDepartment = "SELECT * FROM %1s WHERE id='%2s'";

	private final String _insertDepartment = "INSERT into %1s(id, name) values ('%2$s', '%3$s')";
	private final String _updateDepartment = "UPDATE %1s SET id = '%2$s', name = '%3$s' WHERE id = '%2$s'";
	private final String _deleteDepartment = "DELETE FROM %1s WHERE id= '%2s'";
	

	public String getAllQuery() {
		return String.format(_selectAllDepartments, getTableName());
	}

	public String getQuery(String id) {
		return String.format(_selectDepartment, getTableName(), id);
	}

	public String getInsertQuery(Department object) {
		return String.format(_insertDepartment, getTableName(), object.getId(),
				object.getName());
	}

	public String getUpdateQuery(Department object) {
		return String.format(_updateDepartment, getTableName(), object.getId(),
				object.getName());
	}

	public String getDeleteQuery(Department object) {
		return String.format(_deleteDepartment, getTableName(), object.getId());
	}

	public String getTableName() {
		return DatabaseInformation.getInstance().getDepartmentsTableName();
	}

}
