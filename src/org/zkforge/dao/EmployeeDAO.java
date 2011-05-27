package org.zkforge.dao;

import org.zkforge.beans.Employee;
import org.zkforge.dao.sql.EmployeeQuerySet;
import org.zkforge.dao.sql.QuerySet;

public class EmployeeDAO extends BaseDAO<Employee> {

	private static final QuerySet<Employee> _querySet = new EmployeeQuerySet();
	
	public EmployeeDAO() {
		super(Employee.class, _querySet);
	}
}
