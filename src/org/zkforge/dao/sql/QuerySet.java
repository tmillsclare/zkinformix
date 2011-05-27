package org.zkforge.dao.sql;

public interface QuerySet<T> {
	String getAllQuery();
	String getQuery(String id);
	String getInsertQuery(T object);
	String getUpdateQuery(T object);
	String getDeleteQuery(T object);
	String getTableName();
}
