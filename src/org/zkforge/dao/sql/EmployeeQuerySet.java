package org.zkforge.dao.sql;

import org.zkforge.beans.Employee;
import org.zkforge.dao.DatabaseInformation;

public class EmployeeQuerySet implements QuerySet<Employee> {
	
	private final String selectAllEmployees = "SELECT * FROM " + DatabaseInformation.employeesTableName;
	
	private final String selectEmployee = "SELECT * FROM " + DatabaseInformation.employeesTableName + " WHERE id='%s'";
	
	private final String insertEmployee = "INSERT into " + DatabaseInformation.employeesTableName + "(id, firstname, lastname, age, departmentId) values ('%1$s', '%2$s', '%3$s', %4$d, '%5$s')";
	private final String updateEmployee = "UPDATE " + DatabaseInformation.employeesTableName + " SET id = '%1$s', firstname = '%2$s', lastname = '%3$s', age = %4$d, departmentId = '%5$s' WHERE id = '%1$s'";
	private final String deleteEmployee = "DELETE FROM " + DatabaseInformation.employeesTableName + " WHERE id = '%s'";

	public String getAllQuery() {
		return selectAllEmployees;
	}

	public String getQuery(String id) {
		return String.format(selectEmployee, id);
	}

	public String getInsertQuery(Employee object) {
		return String.format(insertEmployee, object.getId(),
				object.getFirstName(),
				object.getLastName(),
				object.getAge(),
				object.getDepartment().getId());
	}

	public String getUpdateQuery(Employee object) {
		return String.format(updateEmployee, object.getId(),
				object.getFirstName(),
				object.getLastName(),
				object.getAge(),
				object.getDepartment().getId());
	}

	public String getDeleteQuery(Employee object) {
		return String.format(deleteEmployee, object.getId());
	}

	public String getTableName() {
		return DatabaseInformation.employeesTableName;
	}

}
