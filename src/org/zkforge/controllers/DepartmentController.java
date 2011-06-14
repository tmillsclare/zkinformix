package org.zkforge.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.zkforge.beans.Department;
import org.zkforge.dao.DepartmentDAO;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class DepartmentController extends GenericForwardComposer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8365047481595108194L;
	
	DepartmentDAO _depDAO = DepartmentDAO.getInstance();
	Department _currentDepartment = null;
	Textbox txtDepartmentName;
	Listbox lstDepartment;
	
	public void setCurrentDepartment(Department d) {
		this._currentDepartment = d;		
	}
	
	public Department getCurrentDepartment() {
		return _currentDepartment;
	}
	
	public List<Department> getAllDepartments() {
		try {
			return _depDAO.getAll();
		} catch (SQLException e) {
			showError(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void onClick$btnAddDepartment() {
		
		String departmentName = txtDepartmentName.getText();
		
		if(!departmentName.equals("")) {
			Department d = new Department(departmentName + UUID.randomUUID(), //id
										  departmentName);
			
			try {
				_depDAO.insert(d);
			} catch (SQLException e) {
				showError(e.getMessage());
				e.printStackTrace();
			}
		}
		else {
			showError("Please enter a name!");
		}
	}
	
	public void onClick$btnUpdateDepartment() {
		if((lstDepartment.getSelectedItem() != null)) {
			Department d = (Department)(lstDepartment.getSelectedItem().getValue());			
			try {
				_depDAO.update(d);
			} catch (SQLException e) {
				showError(e.getMessage());
				e.printStackTrace();
			}
		}
		else {
			showError("Please select an employee and department!");
		}
	}
	
	public void onClick$btnDeleteDepartment() {
		if(lstDepartment.getSelectedItem() != null) {
			Department d = (Department)(lstDepartment.getSelectedItem().getValue());
			
			try {
				_depDAO.delete(d);
			} catch (SQLException e) {
				
				if (e.getErrorCode() == -692) {
					try {
						Messagebox
								.show("This department still has employees, please move them and then try again");
					} catch (InterruptedException e2) {
						e2.printStackTrace();
					}
				}
				
				e.printStackTrace();
			}
		}
		else {
			showError("Please select a department!");
		}
	}
	
	private void showError(String message) {
		try {
			Messagebox.show(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
