package com.jscanner.ui.component.menu;

import com.jscanner.ui.component.ComponentMenu;
import com.jscanner.ui.component.menuitem.ArchiveMenuItem;

/**
 * The "Scan" menu.
 * 
 * @author Desmond Jackson
 */
public class ScanMenu extends ComponentMenu {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 8779015130538550661L;

	/**
	 * Creates a new "Scan" menu.
	 */
	public ScanMenu() {
		super("Scan");
	}

	@Override
	protected void addMenuItems() {
		add(new ArchiveMenuItem());
	}

}
