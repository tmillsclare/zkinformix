package org.zkforge.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.zkforge.beans.Department;
import org.zkforge.beans.Employee;
import org.zkforge.dao.DepartmentDAO;
import org.zkforge.dao.EmployeeDAO;
import org.zkforge.utils.UiUtils;
import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.ext.Selectable;

public class EmployeeController extends GenericForwardComposer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8381929527653539158L;
	
	private static final Log log = Log.lookup(EmployeeController.class);
	
	
	EmployeeDAO _empDAO = EmployeeDAO.getInstance();
	DepartmentDAO _depDAO = DepartmentDAO.getInstance();
	
	Employee _currentEmployee = null;
	Department _employeeDepartment = null;
	
	Listbox lstEmployee;
	Listbox lstDepartment;
	
	//text boxes
	Textbox txtFirstName;
	Textbox txtLastName;
	
	//int boxes
	Intbox intAge;
	
	public List<Employee> getAllEmployees() {
		try {
			return _empDAO.getAll();
		} catch (SQLException e) {
			final String error = e.getMessage();
			UiUtils.showMessage(error);
			log.error(error);
		}
		
		return null;
	}
	
	public List<Department> getAllDepartments() {
		try {
			return _depDAO.getAll();
		} catch (SQLException e) {
			final String error = e.getMessage();
			UiUtils.showMessage(error);
			log.error(error);
		}
		
		return null;
	}

	public Employee getCurrentEmployee() {
		return _currentEmployee;
	}
	
	public void setCurrentEmployee(Employee e) {
		this._currentEmployee = e;
	}	
	
	//click events
	public void onClick$btnAddEmployee() {
		if(lstDepartment.getSelectedItem() != null) {
			String firstName = txtFirstName.getText();
			String lastName = txtLastName.getText();
			Department dep = null;
			int iAge = Integer.parseInt(intAge.getText());
			
			Selectable selectable = (Selectable)lstDepartment.getModel();
			Set<?> selectedSet = selectable.getSelection();
			
			if (selectedSet.size() == 1) {
				dep = (Department)selectedSet.toArray()[0];
			}
			
			Employee employee = new Employee(firstName + lastName + UUID.randomUUID(), //id
									  firstName,
									  lastName,
									  iAge,
									  dep);
			
			try {
				_empDAO.insert(employee);
			} catch (SQLException e) {
				final String error = e.getMessage();
				UiUtils.showMessage(error);
				log.error(error);
			}
		}
		else {
			UiUtils.showMessage("Please select a department!");
		}
			 
	}
	
	public void onClick$btnUpdateEmployee() {
		if((lstDepartment.getSelectedItem() != null)
			&& (lstEmployee.getSelectedItem() != null)) {
			
			Employee employee = (Employee)(lstEmployee.getSelectedItem().getValue());
			
			try {
				_empDAO.update(employee);
			} catch (SQLException e) {
				final String error = e.getMessage();
				UiUtils.showMessage(error);
				log.error(error);
			}
		}
		else {
			UiUtils.showMessage("Please select an employee and department!");
		} 
	}
	
	public void onClick$btnDeleteEmployee() {
		
		if(lstEmployee.getSelectedItem() != null) {
			Employee employee = (Employee)(lstEmployee.getSelectedItem().getValue());
			
			try {
				_empDAO.delete(employee);
			} catch (SQLException e) {
				final String error = e.getMessage();
				UiUtils.showMessage(error);
				log.error(error);
			}
		}
		else {
			UiUtils.showMessage("Please select an employee!");
		}
			 
	}
	
}
