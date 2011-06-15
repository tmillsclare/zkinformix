package org.zkforge.utils;

import org.zkoss.util.logging.Log;
import org.zkoss.zul.Messagebox;

public final class UiUtils {
	
	private static final Log log = Log.lookup(UiUtils.class);
	
	public static void showMessage(String message) {
		try {
			Messagebox.show(message);
		} catch (InterruptedException e) {
			final String error = ErrorUtils.formatError(ErrorUtils.DISPLAY_ERROR, e);
			UiUtils.showMessage(error);
			log.error(error);
		}
	}
	
}
