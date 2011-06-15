package org.zkforge.utils;

public final class ErrorUtils {
	public static final String CREATE_CONNECTION = "Unable to create connection";
	public static final String CREATE_TABLES = "Unable to create tables";
	public static final String DISPLAY_ERROR = "Unable to display the message";
	
	public static final String formatError(String error, Exception e) {
		StringBuilder strBuilder = new StringBuilder(error);
		strBuilder.append(": \n\nREASON\n\n");
		strBuilder.append(e.getMessage());
		return strBuilder.toString();
	}
	
}
