package org.zkforge.dao;

import org.zkforge.beans.Department;
import org.zkforge.dao.sql.DepartmentQuerySet;
import org.zkforge.dao.sql.QuerySet;

public class DepartmentDAO extends BaseDAO<Department> {
	
	private static final QuerySet<Department> _querySet = new DepartmentQuerySet();
	
	public DepartmentDAO() {
		super(Department.class, _querySet);
	}
		
}
