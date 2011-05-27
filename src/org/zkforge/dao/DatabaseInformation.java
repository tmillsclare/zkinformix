package org.zkforge.dao;

public class DatabaseInformation {
	public static String url = "";
	public static String urlTemplate = "jdbc:informix-sqli://%1$s:%2$d/%3$s:INFORMIXSERVER=%4$s";
	public static String username = "informix";
	public static String password = "hellothere";
	
	public static String employeesTableName = "tblEmployees";
	public static String departmentsTableName = "tblDepartments";
	
}
