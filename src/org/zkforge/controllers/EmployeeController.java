package org.zkforge.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.zkforge.beans.Department;
import org.zkforge.beans.Employee;
import org.zkforge.dao.DepartmentDAO;
import org.zkforge.dao.EmployeeDAO;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.ext.Selectable;

public class EmployeeController extends GenericForwardComposer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8381929527653539158L;
	
	
	EmployeeDAO empDAO = EmployeeDAO.getInstance();
	DepartmentDAO depDAO = DepartmentDAO.getInstance();
	
	Employee currentEmployee = null;
	Department employeeDepartment = null;
	
	Listbox employee_box;
	Listbox department;
	
	//text boxes
	Textbox first_name;
	Textbox last_name;
	
	//int boxes
	Intbox age;
	
	public List<Employee> getAllEmployees() {
		try {
			return empDAO.getAll();
		} catch (SQLException e) {
			showError(e.getMessage());
			e.printStackTrace();
		}
		
		return null;
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

	public Employee getCurrentEmployee() {
		return currentEmployee;
	}
	
	public void setCurrentEmployee(Employee e) {
		this.currentEmployee = e;
	}	
	
	//click events
	public void onClick$addEmployee() {
		if(department.getSelectedItem() != null) {
			String firstName = first_name.getText();
			String lastName = last_name.getText();
			Department dep = null;
			int iAge = Integer.parseInt(age.getText());
			
			Selectable selectable = (Selectable)department.getModel();
			Set<?> selectedSet = selectable.getSelection();
			
			if (selectedSet.size() == 1) {
				dep = (Department)selectedSet.toArray()[0];
			}
			
			Employee e = new Employee(firstName + lastName + UUID.randomUUID(), //id
									  firstName,
									  lastName,
									  iAge,
									  dep);
			
			try {
				empDAO.insert(e);
			} catch (SQLException e1) {
				showError(e1.getMessage());
				e1.printStackTrace();
			}
		}
		else {
			showError("Please select a department!");
		}
			 
	}
	
	public void onClick$updateEmployee() {
		if((department.getSelectedItem() != null)
			&& (employee_box.getSelectedItem() != null)) {
			
			Employee e = (Employee)(employee_box.getSelectedItem().getValue());
			
			try {
				empDAO.update(e);
			} catch (SQLException e1) {
				showError(e1.getMessage());
				e1.printStackTrace();
			}
		}
		else {
			showError("Please select an employee and department!");
		} 
	}
	
	public void onClick$deleteEmployee() {
		
		if(employee_box.getSelectedItem() != null) {
			Employee e = (Employee)(employee_box.getSelectedItem().getValue());
			
			
			try {
				empDAO.delete(e);
			} catch (SQLException e1) {
				
				if (e1.getErrorCode() == -692) {
					try {
						Messagebox
								.show("This department still has employees, please move them and then try again");
					} catch (InterruptedException e2) {
						e2.printStackTrace();
					}
				}
				
				showError(e1.getMessage());
				e1.printStackTrace();
			}
		}
		else {
			showError("Please select an employee!");
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
