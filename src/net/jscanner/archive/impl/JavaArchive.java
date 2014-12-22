package net.jscanner.archive.impl;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import net.jscanner.archive.Archive;

/**
 * Represents a Jar file.
 * 
 * @author Desmond Jackson
 */
public class JavaArchive extends Archive {

	/**
	 * Creates the Jar file representation.
	 * 
	 * @param jarFile The jar file to represent
	 */
	public JavaArchive(JarFile jarFile) {
		super(jarFile);
	}

	@Override
	public void findClasses() {
		Enumeration<JarEntry> jarEntries = jarFile.entries();
		while (jarEntries.hasMoreElements()) {
			JarEntry jarEntry = jarEntries.nextElement();
			String entryName = jarEntry.getName();
			if (entryName.endsWith(".class")) {
				ClassNode classNode = new ClassNode();
				try {
					ClassReader  classReader = new ClassReader(
							jarFile.getInputStream(jarEntry));
					classReader.accept(classNode, ClassReader.SKIP_DEBUG |
							ClassReader.SKIP_FRAMES);
					classes.put(entryName.replace(".class", ""), classNode);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public String getName() {
		return jarFile.getName();
	}

}
