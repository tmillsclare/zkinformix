package org.zkforge.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.zkforge.dao.DatabaseInformation;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;

public class DatabaseSetupController extends GenericForwardComposer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2630079905018065481L;

	private Textbox txtHostname, txtServername, txtUsername, 
					txtPassword, txtDatabaseName, txtEmployeesTableName, 
					txtDepartmentTableName;
	
	private Intbox intPort;
	
	private Button btnCreateTables, btnGotoApplication;
	
	public void onClick$btnCheckConnection() {
		
		String strError = "";
		
		try {
			DatabaseInformation.url = String.format(DatabaseInformation.urlTemplate, 
													txtHostname.getValue(),
													intPort.getValue(),
													txtDatabaseName.getValue(),
													txtServername.getValue());
			
			DatabaseInformation.username = txtUsername.getValue();
			DatabaseInformation.password = txtPassword.getValue();
			
			DatabaseInformation.departmentsTableName = txtDepartmentTableName.getValue();
			DatabaseInformation.employeesTableName = txtEmployeesTableName.getValue();
			
			initializeConnection();
			strError = createConnection();
		} catch (ClassNotFoundException cnfe) {
			strError = cnfe.getMessage();
		}
		finally {
			try {
				Messagebox.show(strError);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void onClick$btnCreateTables() {
		String strError = "Creation Successful!";
		
		try {
			initializeConnection();
			createTables();
		} catch (SQLException ex) {
			strError = ex.getMessage();
		} catch (ClassNotFoundException e) {
			strError = e.getMessage();
		} finally {
			try {
				Messagebox.show(strError);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void initializeConnection() throws ClassNotFoundException {
		Class.forName("com.informix.jdbc.IfxDriver");
	}
	
	private String createConnection() {
		String strMessage = "Connection Sucessful!";
		
		// get connection
		Connection conn;
		try {
			
		
						
			conn = DriverManager.getConnection(DatabaseInformation.url, 
											   DatabaseInformation.username, 
											   DatabaseInformation.password);
			
			conn.close();
			
			btnCreateTables.setDisabled(false);
			btnGotoApplication.setDisabled(false);
		} catch (SQLException e) {
			strMessage = "Connection failed!";
			e.printStackTrace();
		}
		
		return strMessage;
	}
	
	private void createTables() throws SQLException {
		
		String createTableDepartments = "create table " + DatabaseInformation.departmentsTableName + "(id varchar(50), name varchar(50), primary key (id) );";
		String createTableEmployees = "create table  " + DatabaseInformation.employeesTableName + "(id varchar(50), first_name varchar(50),	last_name varchar(50), age integer,	departmentid varchar(50), primary key (id), foreign key (departmentid) references departments(id));";
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = DriverManager.getConnection(DatabaseInformation.url, 
					   									  DatabaseInformation.username, 
					   									  DatabaseInformation.password);
			stmt = conn.createStatement();
	
			stmt.executeUpdate(createTableDepartments);
			stmt.executeUpdate(createTableEmployees);
		} finally {
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
		
	}
}
