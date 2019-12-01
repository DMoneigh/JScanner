package com.jscanner.ui.component;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * Represents a file chooser.
 * 
 * @author Desmond Jackson
 */
public abstract class ComponentFileChooser extends JFileChooser {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 3460153062062410464L;
	
	/**
	 * Creates a new file chooser.
	 */
	public ComponentFileChooser() {
		setFileFilter(getCustomFileFilter());
	}
	
	/**
	 * Gets the custom file filter.
	 * 
	 * @return The custom file filter
	 */
	public abstract FileFilter getCustomFileFilter();
	
}
