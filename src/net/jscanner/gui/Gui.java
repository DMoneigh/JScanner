package net.jscanner.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Represents a graphical user interface.
 * 
 * @author Desmond Jackson
 */
public abstract class Gui extends JFrame {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -6199506668761514960L;
	
	/**
	 * Creates a new graphical user interface.
	 * 
	 * @param title The title of the graphical user interface
	 */
	public Gui(String title) {
		super(title);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(getDefaultCloseOperation());
		setAlwaysOnTop(getAlwaysOnTop());
		setResizable(getResizable());
		setSize(getSize());
		addComponents();
	}
	
	/**
	 * Appends components to this container.
	 */
	public abstract void addComponents();
	
	/**
	 * Gets whether this window should be on top or not.
	 * 
	 * @return true if this window should be on top of other windows
	 */
	public abstract boolean getAlwaysOnTop();
	
	/**
	 * Gets whether this window should be resizable or not.
	 * 
	 * @return true if this windows should be resizable
	 */
	public abstract boolean getResizable();
	
	@Override
	public abstract int getDefaultCloseOperation();
	
	@Override
	public abstract Dimension getSize();
	
}
