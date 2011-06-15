package org.zkforge.dao.sql;

import org.zkforge.beans.Employee;
import org.zkforge.dao.DatabaseInformation;

public class EmployeeQuerySet implements QuerySet<Employee> {

	public String getAllQuery() {
		return String.format(SqlPropertyLoader.getQuery("selectAllEmployees"), getTableName());
	}

	public String getQuery(String id) {
		return String.format(SqlPropertyLoader.getQuery("selectEmployee"), getTableName(), id);
	}

	public String getInsertQuery(Employee object) {
		return String.format(SqlPropertyLoader.getQuery("insertEmployee"), getTableName(), 
				object.getId(),
				object.getFirstName(),
				object.getLastName(),
				object.getAge(),
				object.getDepartment().getId());
	}

	public String getUpdateQuery(Employee object) {
		return String.format(SqlPropertyLoader.getQuery("updateEmployee"), getTableName(), 
				object.getId(),
				object.getFirstName(),
				object.getLastName(),
				object.getAge(),
				object.getDepartment().getId());
	}

	public String getDeleteQuery(Employee object) {
		return String.format(SqlPropertyLoader.getQuery("deleteEmployee"), getTableName(), 
				object.getId());
	}

	public String getTableName() {
		return DatabaseInformation.getInstance().getEmployeesTableName();
	}

}
