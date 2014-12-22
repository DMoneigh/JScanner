package net.jscanner.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

import org.apache.commons.io.FileUtils;

import net.jscanner.archive.Archive;
import net.jscanner.archive.impl.ClassFile;
import net.jscanner.archive.impl.JavaArchive;

/**
 * Manages JScanner's files.
 * 
 * @author Desmond Jackson
 */
public class FileManager extends Object {
	
	/**
	 * The directories in JScanner's home directory.
	 */
	private static File[] DIRECTORIES = {
		getDirectory("plugins")
	};

	/**
	 * Gets a directory in JScanner's home directory.
	 * 
	 * @param directory The directory in JScanner's home directory
	 * 
	 * @return A directory in JScanner's home directory
	 */
	private static File getDirectory(String directory) {
		return new File(System.getProperty("user.home") + "/JScanner/"
				+ directory);
	}
	
	/**
	 * Creates directories in JScanner's home directory.
	 */
	public static void createDirectories() {
		for (File directory : DIRECTORIES)
			directory.mkdirs();
	}
	
	/**
	 * Gets either the rt.jar or classes.jar archive.
	 * 
	 * @return null if either rt.jar or classes.jar could not be found
	 */
	public static JavaArchive getRuntimeArchive() {
		for (File jarFile : FileUtils.listFiles(new File(
				System.getProperty("java.home")), new String[] {"jar"}, true)) {
			String jarFileName = jarFile.getName();
			if (jarFileName.equals("rt.jar") || jarFileName.equals("classes.jar"))
				try {
					return new JavaArchive(new JarFile(jarFile));
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;
	}
	
	/**
	 * Gets the archives in the "plugins" directory.
	 * 
	 * @return The archives in the "plugins" directory
	 */
	public static List<Archive> getPlugins() {
		List<Archive> plugins = new ArrayList<Archive>();
		for (File file : DIRECTORIES[0].listFiles()) {
			String name = file.getName();
			if (name.endsWith(".class"))
				plugins.add(new ClassFile(file));
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
