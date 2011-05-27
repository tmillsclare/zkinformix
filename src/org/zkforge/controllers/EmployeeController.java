package org.zkforge.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.zkforge.beans.Department;
import org.zkforge.beans.Employee;
import org.zkforge.dao.DepartmentDAO;
import org.zkforge.dao.EmployeeDAO;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

public class EmployeeController extends GenericForwardComposer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8381929527653539158L;
	
	
	EmployeeDAO empDAO = new EmployeeDAO();
	DepartmentDAO depDAO = new DepartmentDAO();
	
	Employee currentEmployee = null;
	Department currentDepartment = null;
	
	Listbox employee_box;
	Listbox department_box;
	
	//text boxes
	Textbox first_name;
	Textbox last_name;
	
	//int boxes
	Intbox age;
	
	Textbox department_name;
	
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
	
	public Department getCurrentDepartment() {
		return currentDepartment;
	}
	
	public void setCurrentEmployee(Employee e) {
		this.currentEmployee = e;
		
		Department d = getEmployeeDepartment();
		setCurrentDepartment(d);
		setSelectedDepartment(d);
	}
	
	public void setCurrentDepartment(Department d) {
		this.currentDepartment = d;		
	}
	
	//click events
	public void onClick$addEmployee() {
		if(department_box.getSelectedItem() != null) {
			String firstName = first_name.getText();
			String lastName = last_name.getText();
			int iAge = Integer.parseInt(age.getText());
			
			Department d = (Department)(department_box.getSelectedItem().getValue());
			
			Employee e = new Employee(firstName + lastName + UUID.randomUUID(), //id
									  firstName,
									  lastName,
									  iAge,
									  d.getId());
			
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
		if((department_box.getSelectedItem() != null)
			&& (employee_box.getSelectedItem() != null)) {
			
			Employee e = (Employee)(employee_box.getSelectedItem().getValue());
			Department d = (Department)(department_box.getSelectedItem().getValue());
			
			e.setDepartmentId(d.getId());
			
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
				showError(e1.getMessage());
				e1.printStackTrace();
			}
		}
		else {
			showError("Please select an employee!");
		}
			 
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
	
	
	private Department getEmployeeDepartment() {
		
		Department dep = null;
		
		if(currentEmployee != null)	{
			try {
				dep = depDAO.get(currentEmployee.getDepartmentId());
			} catch (SQLException e) {
				showError(e.getMessage());
				e.printStackTrace();
			}
		}
		
		return dep;
	}
	
	private void setSelectedDepartment(Department d) {
		List<?> allItems = department_box.getItems();
		
		if(d == null)
			return;
		
		for(int i=0; i < allItems.size(); i++) {
			Listitem li = (Listitem)allItems.get(i);
			Object listItemValue = li.getValue();
			
			if(listItemValue instanceof Department) {
				
				Department liDepartment = (Department)li.getValue();
				
				//check the id
				if(d.getId().equals(liDepartment.getId())) {
					department_box.selectItem(li);
					department_name.setText(d.getName());
					break;
				}
			}
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
