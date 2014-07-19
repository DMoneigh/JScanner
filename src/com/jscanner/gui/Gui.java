package com.jscanner.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Represents a graphical user interface.
 * 
 * @author Desmond Jackson
 */
public abstract class Gui extends JFrame {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = -3459233739171770275L;

	/**
	 * Builds the graphical user interface.
	 * 
	 * @param title The title of the graphical user interface.
	 */
	public Gui(String title) {
		super(title);
		setDefaultCloseOperation(getDefaultCloseOperation());
		setLocationRelativeTo(null);
		setAlwaysOnTop(getAlwaysOnTop());
		setResizable(getResizable());
		setSize(getSize());
		addComponents();
	}

	/**
	 * Adds components to the graphical user interface.
	 */
	public abstract void addComponents();

	/**
	 * Gets the default close operation.
	 */
	public abstract int getDefaultCloseOperation();

	/**
	 * Gets the always on top state.
	 * 
	 * @return The always on top state.
	 */
	public abstract boolean getAlwaysOnTop();
	
	/**
	 * Gets whether the graphical user interface can resized or not.
	 * 
	 * @return Whether the graphical user interface can be resized or not.
	 */
	public abstract boolean getResizable();

	/**
	 * Gets the size of the graphical user interface.
	 */
	public abstract Dimension getSize();

}
