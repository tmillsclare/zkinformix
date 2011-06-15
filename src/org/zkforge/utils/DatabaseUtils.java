package org.zkforge.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.zkforge.dao.DatabaseInformation;

public class DatabaseUtils {
	
	public static void testConnection() throws SQLException {
		// get connection
		Connection conn;
		conn = DriverManager.getConnection(DatabaseInformation.getInstance().getUrl(),
											   DatabaseInformation.getInstance().getUsername(), 
											   DatabaseInformation.getInstance().getPassword());
			
		DbUtils.closeQuietly(conn);
	}
	
	public static void createTables() throws SQLException {
		final String createTableDepartments = "create table " + DatabaseInformation.getInstance().getDepartmentsTableName() + "(id varchar(50), name varchar(50), primary key (id) );";
		final String createTableEmployees = "create table  " + DatabaseInformation.getInstance().getEmployeesTableName() + "(id varchar(50), firstname varchar(50),	lastname varchar(50), age integer,	departmentid varchar(50), primary key (id), foreign key (departmentid) references departments(id));";
		
		Connection conn = null;
		QueryRunner run = new QueryRunner();
		
		try {
			conn = DriverManager.getConnection(DatabaseInformation.getInstance().getUrl(),
											   DatabaseInformation.getInstance().getUsername(), 
											   DatabaseInformation.getInstance().getPassword());
			run.update(conn, createTableDepartments);
			run.update(conn, createTableEmployees);

		} finally {
			DbUtils.closeQuietly(conn);
		}
	}
}
