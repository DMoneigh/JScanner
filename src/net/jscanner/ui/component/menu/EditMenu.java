package net.jscanner.ui.component.menu;

import net.jscanner.ui.component.ComponentMenu;
import net.jscanner.ui.component.menuitem.ClearMenuItem;
import net.jscanner.ui.component.menuitem.CopyMenuItem;

/**
 * The "Edit" menu.
 * 
 * @author Desmond Jackson
 */
public class EditMenu extends ComponentMenu {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 1590583007442402446L;

	/**
	 * Creates a new "Edit" menu.
	 */
	public EditMenu() {
		super("Edit");
	}

	@Override
	protected void addMenuItems() {
		add(new CopyMenuItem());
		add(new ClearMenuItem());
	}

}
