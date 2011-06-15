package org.zkforge.dao;

import org.zkforge.beans.Department;
import org.zkforge.dao.sql.DepartmentQuerySet;
import org.zkforge.dao.sql.QuerySet;

public class DepartmentDAO extends AbstractDAO<Department> {
	
	private static final QuerySet<Department> _querySet = new DepartmentQuerySet();
	private static final DepartmentDAO instance = new DepartmentDAO();
	
	private DepartmentDAO() {
		super(Department.class, _querySet, null, null);
	}
	
	public static DepartmentDAO getInstance() {
		return instance;
	}
		
}
