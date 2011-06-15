package org.zkforge.dao.sql;

import java.util.Locale;
import java.util.ResourceBundle;

public class SqlPropertyLoader {
	private static ResourceBundle queries = ResourceBundle.getBundle("org.zkforge.dao.sql.queries", Locale.ROOT);
	
	public static String getQuery(String key) {
		return queries.getString(key);
	}
}
