package net.jscanner.archive;

import org.objectweb.asm.tree.ClassNode;

/**
 * Loads classes from a specified archive.
 * 
 * @author Desmond Jackson
 */
public class ArchiveClassLoader extends ClassLoader {
	
	/**
	 * The archive to load classes from.
	 */
	private Archive archive;
	
	/**
	 * Creates a new archive class loader.
	 * 
	 * @param archive The archive to load classes from
	 */
	public ArchiveClassLoader(Archive archive) {
		super();
		this.archive = archive;
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		String className = name.replaceAll("\\.", "/");
		ClassNode classNode = archive.get(className);
		if (classNode != null) {
			byte[] data = archive.getBytes(className);
			if (data != null) {
				Class<?> clazz = defineClass(name, data, 0, data.length, null);
				return clazz;
			}
		}
		return super.findSystemClass(name);
	}

}
