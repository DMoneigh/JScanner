package net.jscanner.ui.resource;

import javax.swing.ImageIcon;

/**
 * Manages resources for the user interface.
 * 
 * @author Desmond Jackson
 */
public class ResourceManager {
	
	/**
	 * Gets an image icon by file name.
	 * 
	 * @param fileName The file name
	 * 
	 * @return An image icon by file name
	 */
	public static ImageIcon getImage(String fileName) {
		return new ImageIcon(ResourceManager.class.getResource(fileName));
	}

}
