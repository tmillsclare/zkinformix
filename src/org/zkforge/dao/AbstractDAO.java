package org.zkforge.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.zkforge.dao.sql.QuerySet;

public abstract class AbstractDAO<T> {
	
	enum DBACTION { INSERT, UPDATE, DELETE}
	
	ResultSetHandler<List<T>> _listHandler = null;
	ResultSetHandler<T> _beanHandler = null;
	final QuerySet<T> _querySet;
	
	public AbstractDAO(Class<T> baseClass, QuerySet<T> querySet) {
		this(baseClass, querySet, null, null);
	}
	
	public AbstractDAO(Class<T> baseClass, QuerySet<T> querySet, 
				   ResultSetHandler<List<T>> listHandler, ResultSetHandler<T> beanHandler) {
		
		if(baseClass == null) {
			throw new NullPointerException("baseClass cannot be null");
		}
		
		if(querySet == null) {
			throw new NullPointerException("querySet cannot be null");
		}
		
		if(listHandler == null) {
			_listHandler = new BeanListHandler<T>(baseClass);
		} else {
			_listHandler = listHandler;
		}
		
		if(_beanHandler == null) {
			_beanHandler = new BeanHandler<T>(baseClass);
		} else {
			_beanHandler = beanHandler;
		}
		
		_querySet = querySet;
		
		try {
			Class.forName("com.informix.jdbc.IfxDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public List<T> getAll() throws SQLException {
		Connection conn = null;
		QueryRunner run = new QueryRunner();
		List<T> allDepartments = null;

		try {
			conn = DriverManager.getConnection(DatabaseInformation.url,
					DatabaseInformation.username, DatabaseInformation.password);

			allDepartments = run.query(conn, _querySet.getAllQuery(), _listHandler);

		} finally {
			DbUtils.closeQuietly(conn);
		}

		return allDepartments;
	}
	
	public T get(String id) throws SQLException {
		
		Connection conn = null;
		QueryRunner run = new QueryRunner();
		T object = null;
		
		try {
			// get connection
			conn = DriverManager.getConnection(DatabaseInformation.url,
					DatabaseInformation.username, DatabaseInformation.password);
			
			object = run.query(conn, _querySet.getQuery(id), _beanHandler);

		} finally {
			DbUtils.closeQuietly(conn);
		}

		return object;
	}
	
	public boolean delete(T object) throws SQLException {
		return performDatabaseAction(DBACTION.DELETE, object);
	}

	public boolean insert(T object) throws SQLException {
		return performDatabaseAction(DBACTION.INSERT, object);
	}

	public boolean update(T object) throws SQLException {
		return performDatabaseAction(DBACTION.UPDATE, object);
	}
	
	private boolean performDatabaseAction(DBACTION dba, T object) throws SQLException {
		
		Connection conn = null;
		boolean result = false;
		QueryRunner run = new QueryRunner();
		
		if(object == null) {
			throw new NullPointerException("The passed object cannot be null");
		}
		
		// discover what update needs to be performed
		String sQuery = "";

		try {

			switch (dba) {
			case DELETE:
				sQuery = _querySet.getDeleteQuery(object);
				break;

			case INSERT:
				sQuery = _querySet.getInsertQuery(object);
				break;

			case UPDATE:
				sQuery = _querySet.getUpdateQuery(object);
				break;

			default:
				throw new SQLException("No database action specified");
			}

			conn = DriverManager.getConnection(DatabaseInformation.url,
					DatabaseInformation.username, DatabaseInformation.password);

			int rowsUpdated = run.update(conn, sQuery);

			result = (rowsUpdated > 0);
		} finally {
			DbUtils.closeQuietly(conn);
		}

		return result;
	}
}
