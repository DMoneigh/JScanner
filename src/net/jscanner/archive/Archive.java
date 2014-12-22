package net.jscanner.archive;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.jar.JarFile;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

/**
 * Represents either a class file or Java archive.
 * 
 * @author Desmond Jackson
 */
public abstract class Archive extends Object implements Iterable<ClassNode> {
	
	/**
	 * The class file.
	 */
	protected File classFile;
	
	/**
	 * The Java archive.
	 */
	protected JarFile jarFile;
	
	/**
	 * The classes found in the archive.
	 */
	protected Map<String, ClassNode> classes = new TreeMap<String, ClassNode>();
	
	/**
	 * Creates a new class file representation.
	 * 
	 * @param classFile The class file to represent
	 */
	public Archive(File classFile) {
		super();
		this.classFile = classFile;
		findClasses();
	}
	
	/**
	 * Creates a new Java archive representation.
	 * 
	 * @param jarFile The Java archive to represent
	 */
	public Archive(JarFile jarFile) {
		super();
		this.jarFile = jarFile;
		findClasses();
	}
	
	/**
	 * Gets a class node by name.
	 * 
	 * @param name The name of the class node
	 * 
	 * @return null if class node is not found
	 */
	public ClassNode get(String name) {
		return classes.get(name);
	}
	
	/**
	 * Gets the bytes of a class node by name.
	 * 
	 * @param name The name of the class node
	 * 
	 * @return null if the class node is not found
	 */
	public byte[] getBytes(String name) {
		ClassNode classNode = get(name);
		if (classNode == null)
			return null;
		ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		classNode.accept(classWriter);
		return classWriter.toByteArray();
	}
	
	/**
	 * Finds classes in the archive.
	 */
	public abstract void findClasses();
	
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
