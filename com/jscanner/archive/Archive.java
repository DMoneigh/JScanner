package com.jscanner.archive;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.jar.JarFile;

import org.objectweb.asm.tree.ClassNode;

/**
 * Represents either a Java archive or class archive.
 * 
 * @author Desmond Jackson
 */
public abstract class Archive extends Object implements Iterable<ClassNode> {

	/**
	 * The class archive.
	 */
	protected File file;

	/**
	 * The Java archive.
	 */
	protected JarFile jarFile;

	/**
	 * The classes found in the archives.
	 */
	protected Map<String, ClassNode> classes = new TreeMap<String, ClassNode>();

	/**
	 * Loads the classes found in the class archive into memory.
	 * 
	 * @param file The class archive.
	 */
	public Archive(File file) {
		super();
		this.file = file;
		load();
	}

	/**
	 * Loads the classes found in the Java archive into memory.
	 * 
	 * @param jarFile The Java archive.
	 */
	public Archive(JarFile jarFile) {
		super();
		this.jarFile = jarFile;
		load();
	}

	/**
	 * Gets a class from the archive based off its name.
	 * 
	 * @param name The name of the class.
	 * 
	 * @return A class from the archive based off its name.
	 */
	public ClassNode getClassNode(String name) {
		for (ClassNode classNode : this)
			if (classNode.name.equals(name))
				return classNode;
		return null;
	}

	@Override
	public Iterator<ClassNode> iterator() {
		return classes.values().iterator();
	}

	/**
	 * Loads the classes found in an archive into memory.
	 * 
	 * @throws IOException
	 */
	public abstract void load();

	/**
	 * Gets the name of the archive.
	 * 
	 * @return The name of the archive.
	 */
	public abstract String getName();

}
