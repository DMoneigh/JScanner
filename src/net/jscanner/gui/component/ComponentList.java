package net.jscanner.gui.component;

import javax.swing.JComponent;
import javax.swing.JList;

/**
 * Represents a list.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentList extends JList<JComponent> {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 7773780671578085691L;
	
	/**
	 * Creates a new list.
	 */
	public ComponentList() {
		super();
		addComponents();
	}
	
	/**
	 * Appends components to this list.
	 */
	public abstract void addComponents();
	
}
