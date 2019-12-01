package com.jscanner.cli.command.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;

import com.jscanner.archive.Archive;
import com.jscanner.archive.ArchiveScanner;
import com.jscanner.archive.impl.ClassFile;
import com.jscanner.archive.impl.JavaArchive;
import com.jscanner.cli.command.Command;

/**
 * The "scan" command.
 * 
 * @author Desmond Jackson
 */
public class ScanCommand extends Command {

	/**
	 * Creates a new "scan" command instance.
	 */
	public ScanCommand() {
		super("scan");
	}

	@Override
	public boolean execute(String... arguments) {
		File file = new File(arguments[0]);
		if (file.exists()) {
			Archive archive = getArchive(file);
			if (archive != null) {
				System.out.println(new ArchiveScanner(archive).scan(getClasses(arguments)));
				
				if (archive instanceof JavaArchive)
					((JavaArchive) archive).close();

				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the classes and their children methods from the arguments.
	 * 
	 * @param arguments The arguments
	 * 
	 * @return The classes and their children methods from the arguments
	 */
	private Map<String, List<String>> getClasses(String... arguments) {
		Map<String, List<String>> classes = new HashMap<String, List<String>>();
		List<String> methods = new ArrayList<String>();
		String clazz = "";
		boolean flag = false;
		for (int i = 1; i < arguments.length; i++) {
			String argument = arguments[i];
			if (argument.contains("/")) {
				if (flag) classes.put(clazz, methods);
				clazz = argument;
				methods = new ArrayList<String>();
				flag = true;
			} else
				methods.add(argument);
		}
		classes.put(clazz, methods);
		return classes;
	}

	/**
	 * Derives an archive from a file.
	 * 
	 * @param file The file
	 * 
	 * @return The derived archive from the file
	 */
	private Archive getArchive(File file) {
		String name = file.getName();
		if (name.endsWith(".class"))
			return new ClassFile(file);
		else if (name.endsWith(".jar"))
			try {
				return new JavaArchive(new JarFile(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public String getUsage() {
		return "scan <archive path> <package/class name> <method names>...";
	}

	@Override
	public String getHelp() {
		return "scans an archive for the specified class and method calls";
	}

}
