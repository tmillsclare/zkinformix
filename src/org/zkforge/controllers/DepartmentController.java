package org.zkforge.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.zkforge.beans.Department;
import org.zkforge.dao.DepartmentDAO;
import org.zkforge.utils.UiUtils;
import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

public class DepartmentController extends GenericForwardComposer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8365047481595108194L;
	
	private static final Log log = Log.lookup(DepartmentController.class);
	
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
			final String error = e.getMessage();
			UiUtils.showMessage(error);
			log.error(error);
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
				final String error = e.getMessage();
				UiUtils.showMessage(error);
				log.error(error);
			}
		}
		else {
			UiUtils.showMessage("Please enter a name!");
		}
	}
	
	public void onClick$btnUpdateDepartment() {
		if((lstDepartment.getSelectedItem() != null)) {
			Department d = (Department)(lstDepartment.getSelectedItem().getValue());			
			try {
				_depDAO.update(d);
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
	
	public void onClick$btnDeleteDepartment() {
		if(lstDepartment.getSelectedItem() != null) {
			Department d = (Department)(lstDepartment.getSelectedItem().getValue());
			
			try {
				_depDAO.delete(d);
			} catch (SQLException e) {
				
				if (e.getErrorCode() == -692) {
					UiUtils.showMessage("This department still has employees, please move them and then try again");
				} else {
					final String error = e.getMessage();
					UiUtils.showMessage(error);
					log.error(error);
				}
			}
		}
		else {
			UiUtils.showMessage("Please select a department!");
		}
	}
	
}
