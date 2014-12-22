package net.jscanner.gui.component.impl;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.jscanner.archive.Archive;
import net.jscanner.archive.impl.ClassFile;
import net.jscanner.archive.impl.JavaArchive;
import net.jscanner.gui.component.ComponentFileChooser;

/**
 * The archive file chooser.
 * 
 * @author Desmond Jackson
 */
public class FileChooserArchive extends ComponentFileChooser {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -839003250916987964L;

	/**
	 * The component that invoked this class
	 */
	private Component component;

	/**
	 * Creates a new archive file chooser.
	 * 
	 * @param component The component that invoked this class
	 */
	public FileChooserArchive(Component component) {
		super();
		this.component = component;
	}

	/**
	 * Gets the archive selected by the user.
	 * 
	 * @return null if no archive was selected or if an error occurred
	 */
	public Archive getSelectedArchive() {
		if (showOpenDialog(component) == 0) {
			File selectedFile = getSelectedFile();
			if (selectedFile.getName().endsWith(".class"))
				return new ClassFile(selectedFile);
			else
				try {
					return new JavaArchive(new JarFile(selectedFile));
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;
	}

	@Override
	public FileFilter getCustomFileFilter() {
		return new FileNameExtensionFilter("Archives",
				new String[] {"class", "jar"});
	}

}
