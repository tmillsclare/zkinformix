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
	
	DepartmentDAO depDAO = DepartmentDAO.getInstance();
	Department currentDepartment = null;
	Textbox department_name;
	Listbox department_box;
	
	public void setCurrentDepartment(Department d) {
		this.currentDepartment = d;		
	}
	
	public Department getCurrentDepartment() {
		return currentDepartment;
	}
	
	public List<Department> getAllDepartments() {
		try {
			return depDAO.getAll();
		} catch (SQLException e) {
			showError(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void onClick$addDepartment() {
		
		String departmentName = department_name.getText();
		
		if(!departmentName.equals("")) {
			Department d = new Department(departmentName + UUID.randomUUID(), //id
										  departmentName);
			
			try {
				depDAO.insert(d);
			} catch (SQLException e) {
				showError(e.getMessage());
				e.printStackTrace();
			}
		}
		else {
			showError("Please enter a name!");
		}
	}
	
	public void onClick$updateDepartment() {
		if((department_box.getSelectedItem() != null)) {
			Department d = (Department)(department_box.getSelectedItem().getValue());			
			try {
				depDAO.update(d);
			} catch (SQLException e) {
				showError(e.getMessage());
				e.printStackTrace();
			}
		}
		else {
			showError("Please select an employee and department!");
		}
	}
	
	public void onClick$deleteDepartment() {
		if(department_box.getSelectedItem() != null) {
			Department d = (Department)(department_box.getSelectedItem().getValue());
			
			try {
				depDAO.delete(d);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
