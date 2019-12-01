package com.jscanner.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.jar.JarFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import com.jscanner.archive.impl.JavaArchive;

/**
 * Manages the files of JScanner.
 * 
 * @author Desmond Jackson
 */
public class FileManager {
	
	/**
	 * All necessary directories.
	 */
	private static final Map<String, File> DIRECTORIES = new HashMap<String, File>();
	
	/**
	 * The Java Runtime Environment archive's classes and their children methods.
	 */
	private static Map<String, List<String>> classes;
	
	static {
		DIRECTORIES.put("home", getDirectory(""));
	}
	
	/**
	 * Gets a directory by name.
	 * 
	 * @param name The name of the directory
	 * 
	 * @return A directory by name
	 */
	private static File getDirectory(String name) {
		return new File(System.getProperty("user.home") + "/JScanner/" + name);
	}
	
	/**
	 * Creates all necessary directories.
	 */
	public static void createDirectories() {
		for (File directory : DIRECTORIES.values())
			directory.mkdirs();
	}
	
	/**
	 * Gets the Java Runtime Environment archive's classes and their children methods.
	 * 
	 * @return The Java Runtime Environment archive's classes and their children methods
	 */
	public static Map<String, List<String>> getRuntimeClasses() {
		if (FileManager.classes != null) return FileManager.classes;
		Map<String, List<String>> classes = new TreeMap<String, List<String>>();
		for (ClassNode node : FileManager.getRuntimeArchive()) {
			List<String> methodNames = new ArrayList<String>();
			for (Object object : node.methods.toArray())
				if (object instanceof MethodNode) {
					String name = ((MethodNode) object).name;
					if (!methodNames.contains(name))
						methodNames.add(name);
				}
			classes.put(node.name, methodNames);
		}
		return (FileManager.classes = classes);
	}
	
	/**
	 * Gets the System's Java Runtime Environment archive.
	 * 
	 * @return The System's Java Runtime Environment archive
	 */
	private static JavaArchive getRuntimeArchive() {
		//TODO Find rt.jar for later Java version....?????
		for (File jar : FileUtils.listFiles(new File(System.getProperty("java.home")), new String[] {"jar"}, true)) {
			String name = jar.getName();
			if (name.equalsIgnoreCase("rt.jar") || name.equalsIgnoreCase("classes.jar"))
				try {
					return new JavaArchive(new JarFile(jar));
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;
	}
	
	/**
	 * Gets the contents of a file.
	 * 
	 * @param file The file
	 * 
	 * @return The contents of a file
	 */
	public static String contents(String file) {
		String string = "";
		try {
			for (String s : IOUtils.readLines(FileManager.class.getResourceAsStream(file), Charset.defaultCharset()))
				string += s;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return string;
	}
	
	/**
	 * Gets the lines from a file.
	 * 
	 * @param file The file
	 * 
	 * @return null if lines could not be read
	 */
	public static List<String> lines(String file) {
		try {
			return IOUtils.readLines(new FileInputStream(file), Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
