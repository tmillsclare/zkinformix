package org.zkforge.dao.sql;

import org.zkforge.beans.Department;
import org.zkforge.dao.DatabaseInformation;

public class DepartmentQuerySet implements QuerySet<Department> {
	
	private final String selectAllDepartments = "SELECT * FROM " + getTableName();
	private final String selectDepartment = "SELECT * FROM " + DatabaseInformation.departmentsTableName + " WHERE id='%s'";

	private final String insertDepartment = "INSERT into " + DatabaseInformation.departmentsTableName + "(id, name) values ('%1$s', '%2$s')";
	private final String updateDepartment = "UPDATE " + DatabaseInformation.departmentsTableName + " SET id = '%1$s', name = '%2$s' WHERE id = '%1$s'";
	private final String deleteDepartment = "DELETE FROM " + DatabaseInformation.departmentsTableName + " WHERE id= '%s'";
	

	public String getAllQuery() {
		return selectAllDepartments;
	}

	public String getQuery(String id) {
		return String.format(selectDepartment, id);
	}

	public String getInsertQuery(Department object) {
		return String.format(insertDepartment, object.getId(),
				object.getName());
	}

	public String getUpdateQuery(Department object) {
		return String.format(updateDepartment, object.getId(),
				object.getName());
	}

	public String getDeleteQuery(Department object) {
		return String.format(deleteDepartment, object.getId());
	}

	public String getTableName() {
		return DatabaseInformation.departmentsTableName;
	}

}
