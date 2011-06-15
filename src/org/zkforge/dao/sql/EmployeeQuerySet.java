package org.zkforge.dao.sql;

import org.zkforge.beans.Employee;
import org.zkforge.dao.DatabaseInformation;

public class EmployeeQuerySet implements QuerySet<Employee> {
	
	private final String _selectAllEmployees = "SELECT * FROM %1s";
	
	private final String _selectEmployee = "SELECT * FROM %1s WHERE id='%2s'";
	
	private final String _insertEmployee = "INSERT into %1s(id, firstname, lastname, age, departmentId) values ('%2$s', '%3$s', '%4$s', %5$d, '%6$s')";
	private final String _updateEmployee = "UPDATE %1s SET id = '%2$s', firstname = '%3$s', lastname = '%4$s', age = %5$d, departmentId = '%6$s' WHERE id = '%2$s'";
	private final String _deleteEmployee = "DELETE FROM %1s WHERE id = '%2s'";

	public String getAllQuery() {
		return String.format(_selectAllEmployees, getTableName());
	}

	public String getQuery(String id) {
		return String.format(_selectEmployee, getTableName(), id);
	}

	public String getInsertQuery(Employee object) {
		return String.format(_insertEmployee, getTableName(), 
				object.getId(),
				object.getFirstName(),
				object.getLastName(),
				object.getAge(),
				object.getDepartment().getId());
	}

	public String getUpdateQuery(Employee object) {
		return String.format(_updateEmployee, getTableName(), 
				object.getId(),
				object.getFirstName(),
				object.getLastName(),
				object.getAge(),
				object.getDepartment().getId());
	}

	public String getDeleteQuery(Employee object) {
		return String.format(_deleteEmployee, getTableName(), 
				object.getId());
	}

	public String getTableName() {
		return DatabaseInformation.getInstance().getEmployeesTableName();
	}

}
