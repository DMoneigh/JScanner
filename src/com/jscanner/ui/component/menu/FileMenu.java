package com.jscanner.ui.component.menu;

import com.jscanner.ui.component.ComponentMenu;
import com.jscanner.ui.component.menuitem.ExitMenuItem;

/**
 * The "File" menu.
 * 
 * @author Desmond Jackson
 */
public class FileMenu extends ComponentMenu {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -960993168362852063L;

	/**
	 * Creates a new "File" menu.
	 */
	public FileMenu() {
		super("File");
	}

	@Override
	protected void addMenuItems() {
		add(new ExitMenuItem());
	}
	
}
