package com.jscanner.ui.component.menu;

import com.jscanner.ui.component.ComponentMenu;

/**
 * The "Tools" menu.
 * 
 * @author Desmond Jackson
 */
public class ToolsMenu extends ComponentMenu {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 4791928600910329061L;

	/**
	 * Creates a new "Tools" menu.
	 */
	public ToolsMenu() {
		super("Tools");
	}

	@Override
	protected void addMenuItems() {
		add(new ScanMenu());
	}

}
