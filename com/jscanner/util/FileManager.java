package com.jscanner.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.jscanner.archive.Archive;
import com.jscanner.archive.impl.ClassArchive;
import com.jscanner.archive.impl.JavaArchive;
import com.jscanner.gui.component.ComponentMenuItem;

/**
 * Manages the files of JScanner.
 * 
 * @author Desmond Jackson
 */
public class FileManager extends Object {

	/**
	 * The directories in JScanner's home directory.
	 */
	private static final File[] DIRECTORIES = {
		getDirectory("/plugins")
	};

	/**
	 * Gets a directory in JScanner's home directory.
	 * 
	 * @param directory The directory in JScanner's home directory.
	 * 
	 * @return Gets a directory in JScanner's home directory.
	 */
	private static File getDirectory(String directory) {
		return new File(System.getProperty("user.home") + "/JScanner/" 
				+ directory);
	}

	/**
	 * Creates the directories in JScanner's home directory.
	 */
	public static void createDirectories() {
		for (File directory : DIRECTORIES)
			directory.mkdirs();
	}

	/**
	 * Gets an archive selected by the user.
	 * 
	 * @param componentMenuItem The parent menu item.
	 * 
	 * @return An archive selected by the user.
	 */
	public static Archive getSelectedArchive(
			ComponentMenuItem componentMenuItem) {
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileFilter(new FileNameExtensionFilter("Archives",
				new String[] {"class", "jar"}));
		if (jFileChooser.showOpenDialog(componentMenuItem) == 0) {
			File selectedFile = jFileChooser.getSelectedFile();
			if (selectedFile.getName().endsWith(".class"))
				return new ClassArchive(selectedFile);
			else
				try {
					return new JavaArchive(new JarFile(selectedFile));
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;
	}

	/**
	 * Gets all archives in the "plugins" folder.
	 * 
	 * @return All archives in the "plugins" folder.
	 */
	public static List<Archive> getPlugins() {
		List<Archive> plugins = new ArrayList<Archive>();
		for (File file : DIRECTORIES[0].listFiles()) {
			String name = file.getName();
			if (name.endsWith(".class"))
				plugins.add(new ClassArchive(file));
			else if (name.endsWith(".jar"))
				try {
					plugins.add(new JavaArchive(new JarFile(file)));
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return plugins;
	}

}
