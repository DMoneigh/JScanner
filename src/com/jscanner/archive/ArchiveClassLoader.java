package com.jscanner.archive;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.objectweb.asm.tree.ClassNode;

/**
 * The Archive class loader.
 * 
 * @author Desmond Jackson
 */
public class ArchiveClassLoader extends ClassLoader {

	/**
	 * The archive to load.
	 */
	private Archive archive;

	/**
	 * The main class.
	 */
	private Class<?> mainClass;

	/**
	 * Creates the archive class loader.
	 * 
	 * @param archive The archive to load
	 */
	public ArchiveClassLoader(Archive archive) {
		this.archive = archive;
	}

	/**
	 * Gets the main class.
	 * 
	 * @return The main class
	 */
	public Class<?> getMainClass() {
		return mainClass;
	}

	@Override
	public Class<?> loadClass(String name, boolean resolve)	throws ClassNotFoundException {
		String className = name.replaceAll("\\.", "/");
		ClassNode node = archive.getClassNode(className);
		
		if (node != null) {
			byte[] bytes = archive.getBytesOfClassNode(node);
			Class<?> clazz = defineClass(name, bytes, 0, bytes.length);
			
			if (clazz.getName().equals(archive.getMainClassName()))
				mainClass = clazz;
			
			if (resolve)
				resolveClass(clazz);
			
			return clazz;
		}
		return super.findSystemClass(name);
	}

	@Override
	public URL getResource(String name) {
		try {
			File file = new File(name.contains("/") ? name.substring(name.lastIndexOf("/") + 1) : name);
			FileOutputStream fos = new FileOutputStream(file);
			
			fos.write(archive.getBytesOfOtherFile(name));
			fos.close();
			file.deleteOnExit();
			
			return file.toURI().toURL();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public InputStream getResourceAsStream(String name) {
		return new ByteArrayInputStream(archive.getBytesOfOtherFile(name));
	}

}
