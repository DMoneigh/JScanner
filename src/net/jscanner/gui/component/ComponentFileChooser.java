package net.jscanner.gui.component;

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
	private static final long serialVersionUID = 3544253734512627148L;
	
	/**
	 * Creates a new file chooser.
	 */
	public ComponentFileChooser() {
		super();
		setFileFilter(getCustomFileFilter());
	}
	
	/**
	 * Gets a custom file filter.
	 * 
	 * @return A custom file filter
	 */
	public abstract FileFilter getCustomFileFilter();
}
