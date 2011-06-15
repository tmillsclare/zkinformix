package org.zkforge.utils;

import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.WebAppInit;

public class WebAppInitialization implements WebAppInit {

	@Override
	public void init(WebApp wapp) throws Exception {
		//let's initialize the class
		Class.forName("com.informix.jdbc.IfxDriver");
	}

}
