package org.zkforge.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.zkforge.beans.Department;
import org.zkforge.beans.Employee;
import org.zkforge.dao.sql.EmployeeQuerySet;
import org.zkforge.dao.sql.QuerySet;

public class EmployeeDAO extends AbstractDAO<Employee> {

	private static final QuerySet<Employee> _querySet = new EmployeeQuerySet();
	private static final EmployeeDAO _instance = new EmployeeDAO();	
	
	private EmployeeDAO() {
		super(Employee.class, _querySet, 
			new BeanListHandler<Employee>(Employee.class) {
				public List<Employee> handle(ResultSet rs) throws SQLException {
					final List<Employee> employees = new ArrayList<Employee>();
					
					while(rs.next()) {
						employees.add(getEmployeeFromRow(rs));
					}
					
					return employees;
				}
			}, new BeanHandler<Employee>(Employee.class) {
				public Employee handle(ResultSet rs) throws SQLException {
					
					if(!rs.next()) {
						return null;
					}
					
					return getEmployeeFromRow(rs);
				}
			});
	}
	
	public static EmployeeDAO getInstance() {
		return _instance;
	}
	
	private static Employee getEmployeeFromRow(ResultSet rs) throws SQLException {
		String id = rs.getString("id");
		String firstName = rs.getString("firstName");
		String lastName = rs.getString("lastName");
		int age = rs.getInt("age");
		
		Department d = DepartmentDAO.getInstance().get(rs.getString("departmentid"));
		
		return new Employee(id, firstName, lastName, age, d);
	}
}
