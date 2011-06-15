package org.zkforge.utils;

import org.zkoss.zk.ui.util.Clients;

public final class UiUtils {	
	public static void showMessage(String message) {
		Clients.alert(message);
	}
}
