package org.zkforge.dao.sql;

import org.zkforge.beans.Department;
import org.zkforge.dao.DatabaseInformation;

public class DepartmentQuerySet implements QuerySet<Department> {

	public String getAllQuery() {
		return String.format(SqlPropertyLoader.getQuery("selectAllDepartments"), getTableName());
	}

	public String getQuery(String id) {
		return String.format(SqlPropertyLoader.getQuery("selectDepartment"), getTableName(), id);
	}

	public String getInsertQuery(Department object) {
		return String.format(SqlPropertyLoader.getQuery("insertDepartment"), getTableName(), object.getId(),
				object.getName());
	}

	public String getUpdateQuery(Department object) {
		return String.format(SqlPropertyLoader.getQuery("updateDepartment"), getTableName(), object.getId(),
				object.getName());
	}

	public String getDeleteQuery(Department object) {
		return String.format(SqlPropertyLoader.getQuery("deleteDepartment"), getTableName(), object.getId());
	}

	public String getTableName() {
		return DatabaseInformation.getInstance().getDepartmentsTableName();
	}

}
