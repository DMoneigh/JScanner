package net.jscanner.ui;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;

/**
 * Represents a user interface.
 * 
 * @author Desmond Jackson
 */
public abstract class UI extends JFrame {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 3762780713188315826L;
	
	/**
	 * Creates a new user interface.
	 * 
	 * @param title The title
	 */
	public UI(String title) {
		super(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(getDimension());
		addComponents();
	}
	
	/**
	 * Gets the user interface instance from the specified component.
	 * 
	 * @param component The specified component
	 * 
	 * @return null if the instance could not be obtained
	 */
	public static UI getInstance(Component component) {
		Component parent = component.getParent();
		if (parent instanceof JPopupMenu)
			return getInstance(((JPopupMenu) parent).getInvoker());
		while (parent != null) {
			if (parent instanceof UI)
				return (UI) parent;
			parent = parent.getParent();
		}
		return null;
	}
	
	/**
	 * Appends components to the interface.
	 */
	protected abstract void addComponents();
	
	/**
	 * Gets the dimension.
	 * 
	 * @return The dimension
	 */
	protected abstract Dimension getDimension();

}
