package com.jscanner.archive;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.jar.JarFile;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

/**
 * Represents either a class file or Java archive.
 * 
 * @author Desmond Jackson
 */
public abstract class Archive implements Iterable<ClassNode> {

	/**
	 * The class file to represent.
	 */
	protected File classFile;

	/**
	 * The jar file to represent.
	 */
	protected JarFile jarFile;

	/**
	 * The classes found in the archive.
	 */
	private Map<String, ClassNode> classes = new TreeMap<String, ClassNode>();
	
	/**
	 * The other files found in the represented Jar file.
	 */
	protected Map<String, byte[]> otherFiles = new HashMap<String, byte[]>();

	/**
	 * Creates a new archive from a class file.
	 * 
	 * @param classFiles The class file
	 */
	protected Archive(File classFile) {
		this.classFile = classFile;
		findClasses();
	}

	/**
	 * Creates a new archive from a jar file.
	 * 
	 * @param jarFile The jar file
	 */
	protected Archive(JarFile jarFile) {
		this.jarFile = jarFile;
		findClasses();
	}

	/**
	 * Adds a class to the map of classes found in the archive.
	 * 
	 * @param bytes The bytes of the class
	 */
	protected void addClass(byte[] bytes) {
		ClassNode node = new ClassNode();
		ClassReader reader = new ClassReader(bytes);
		reader.accept(node, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
		classes.put(node.name, node);
	}
	
	/**
	 * Adds a class node by class.
	 * 
	 * @param clazz The class
	 */
	public void addClassNode(Class<?> clazz) {
		try {
			ClassReader cr = new ClassReader(clazz.getName());
			ClassNode node = new ClassNode();
			cr.accept(node, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
			classes.put(node.name, node);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the bytes of an other file.
	 * 
	 * @param name The other file name
	 * 
	 * @return The bytes of an other file
	 */
	public byte[] getBytesOfOtherFile(String name) {
		return otherFiles.get(name);
	}

	/**
	 * Gets the bytes of a class node.
	 * 
	 * @param node The class node
	 * 
	 * @return The bytes of a class node
	 */
	public byte[] getBytesOfClassNode(ClassNode node) {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		node.accept(cw);
		return cw.toByteArray();
	}
	
	/**
	 * Gets a class node by name.
	 * 
	 * @param name The class node name
	 * 
	 * @return A class node by name
	 */
	public ClassNode getClassNode(String name) {
		return classes.get(name);
	}

	/**
	 * Gets the main class name.
	 * 
	 * @return null if the main class could not be obtained
	 */
	public String getMainClassName() {
		try {
			
			return jarFile.getManifest().getMainAttributes().getValue("Main-Class");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Finds classes in the archive.
	 */
	protected abstract void findClasses();

	/**
	 * Gets the name of the archive.
	 * 
	 * @return The name of the archive
	 */
	public abstract String getName();

	@Override
	public Iterator<ClassNode> iterator() {
		return classes.values().iterator();
	}

}
