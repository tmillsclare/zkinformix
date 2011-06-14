package org.zkforge.controllers;

import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.api.Tabpanel;

public class MainController extends GenericForwardComposer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3135206455666969851L;
	
	Tabbox tabs;

	public void onSelect$tabs(ForwardEvent e) {
		Object o = e.getOrigin().getTarget();
		
		if (o instanceof Tab) {
			Tab t = (Tab)o;
			Tabpanel tp = t.getLinkedPanel();
			tp.invalidate();
		}
	}
}
