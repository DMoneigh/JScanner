package net.jscanner.ui.component.menu;

import net.jscanner.ui.component.ComponentMenu;
import net.jscanner.ui.component.checkbox.AlwaysOnTopCheckBox;
import net.jscanner.ui.component.checkbox.ResizableCheckBox;
import net.jscanner.ui.component.menuitem.NewWindowMenuItem;

/**
 * The "Window" menu.
 * 
 * @author Desmond Jackson
 */
public class WindowMenu extends ComponentMenu {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 6342268725328513084L;

	/**
	 * Creates a new "Window" menu.
	 */
	public WindowMenu() {
		super("Window");
	}

	@Override
	protected void addMenuItems() {
		add(new NewWindowMenuItem());
		add(new AlwaysOnTopCheckBox());
		add(new ResizableCheckBox());
	}

}
