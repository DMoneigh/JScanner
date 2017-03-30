package net.jscanner.archive;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.jar.JarFile;

import org.objectweb.asm.ClassReader;
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
