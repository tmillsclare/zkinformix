package org.zkforge.dao;

public class DatabaseInformation {

	private static DatabaseInformation dbInformation = new DatabaseInformation();
	
	private String _urlTemplate = "jdbc:informix-sqli://%1$s:%2$d/%3$s:INFORMIXSERVER=%4$s";
	private String _username = "informix";
	private String _password = "";
	
	private String _employeesTableName = "employees";
	private String _departmentsTableName = "departments";
	
	private String _hostname = "localhost";
	private Integer _port = 9088;
	private String _databaseName = "employeedb";
	private String _serverName = "svc_drda_2";
	
	
	
	private DatabaseInformation() {
		//singleton
	}
	
	public static DatabaseInformation getInstance() {
		return dbInformation;
	}
	
	public String getUrl() {
		return String.format(_urlTemplate, getHostname(), getPort(), getDatabaseName(), getServerName());
	}

	public String getUsername() {
		return _username;
	}

	public void setUsername(String _username) {
		this._username = _username;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String _password) {
		this._password = _password;
	}

	public String getEmployeesTableName() {
		return _employeesTableName;
	}

	public void setEmployeesTableName(String _employeesTableName) {
		this._employeesTableName = _employeesTableName;
	}

	public String getDepartmentsTableName() {
		return _departmentsTableName;
	}

	public void setDepartmentsTableName(String _departmentsTableName) {
		this._departmentsTableName = _departmentsTableName;
	}

	public String getHostname() {
		return _hostname;
	}

	public void setHostname(String _hostname) {
		this._hostname = _hostname;
	}

	public Integer getPort() {
		return _port;
	}

	public void setPort(Integer _port) {
		this._port = _port;
	}

	public String getDatabaseName() {
		return _databaseName;
	}

	public void setDatabaseName(String _databaseName) {
		this._databaseName = _databaseName;
	}

	public String getServerName() {
		return _serverName;
	}

	public void setServerName(String _serverName) {
		this._serverName = _serverName;
	}

}
