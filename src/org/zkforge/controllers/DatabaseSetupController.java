package org.zkforge.controllers;

import java.sql.SQLException;

import org.zkforge.dao.DatabaseInformation;
import org.zkforge.utils.DatabaseUtils;
import org.zkforge.utils.ErrorUtils;
import org.zkforge.utils.UiUtils;
import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;

public class DatabaseSetupController extends GenericForwardComposer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2630079905018065481L;
	
	private static final Log log = Log.lookup(DatabaseSetupController.class);
	
	private Button btnCreateTables, btnGotoApplication;
	
	public DatabaseInformation getDatabaseInformation() {
		return DatabaseInformation.getInstance();
	}
	
	public void onClick$btnCheckConnection() {
		
		try {		
			createConnection();
			UiUtils.showMessage("Connection successful");
		} catch (SQLException e) {
			UiUtils.showMessage(ErrorUtils.CREATE_CONNECTION);
			log.error(ErrorUtils.CREATE_CONNECTION, e);
		} 
	}
	
	public void onClick$btnCreateTables() {	
		try {
			DatabaseUtils.createTables();
			UiUtils.showMessage("Tables created successfully, please proceed to the application");
		} catch (SQLException e) {
			UiUtils.showMessage(ErrorUtils.CREATE_TABLES);
			log.error(ErrorUtils.CREATE_TABLES, e);
		}
	}
	
	
	private void createConnection() throws SQLException {
		DatabaseUtils.testConnection();
		
		btnCreateTables.setDisabled(false);
		btnGotoApplication.setDisabled(false);
	}
}
