package net.jscanner.ui.component.filechooser;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.jscanner.archive.Archive;
import net.jscanner.archive.impl.ClassFile;
import net.jscanner.archive.impl.JavaArchive;
import net.jscanner.ui.component.ComponentFileChooser;

/**
 * The "Archives" file chooser.
 * 
 * @author Desmond Jackson
 */
public class ArchiveFileChooser extends ComponentFileChooser {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -2846583962767273693L;
	
	/**
	 * The component that invoked the file chooser.
	 */
	private Component invoker;
	
	/**
	 * Creates a new "Archives" file chooser.
	 * 
	 * @param invoker The component that invoked the file chooser
	 */
	public ArchiveFileChooser(Component invoker) {
		this.invoker = invoker;
	}
	
	/**
	 * Gets the selected archive.
	 * 
	 * @return The selected archive
	 */
	public Archive getSelectedArchive() {
		if (showOpenDialog(invoker) == 0) {
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
		return new FileNameExtensionFilter("Archives", new String[] {"class", "jar"});
	}

}
