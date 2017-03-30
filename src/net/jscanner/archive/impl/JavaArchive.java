package net.jscanner.archive.impl;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.IOUtils;

import net.jscanner.archive.Archive;

/**
 * Represents a jar file.
 * 
 * @author Desmond Jackson
 */
public class JavaArchive extends Archive {

	/**
	 * Creates a new jar file representation.
	 * 
	 * @param jarFile The jar file to represent
	 */
	public JavaArchive(JarFile jarFile) {
		super(jarFile);
	}

	@Override
	protected void findClasses() {
		try {
			Enumeration<JarEntry> entries = jarFile.entries();
			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				if (entry.getName().endsWith(".class"))
					addClass(IOUtils.toByteArray(jarFile.getInputStream(entry)));
			}
			jarFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return jarFile.getName();
	}

}
