package com.jscanner.archive.impl;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import com.jscanner.archive.Archive;

/**
 * Represents a Jar file.
 * 
 * @author Desmond Jackson
 */
public class JavaArchive extends Archive {

	/**
	 * Creates the Java Archive representation.
	 * 
	 * @param jarFile The Jar file to represent.
	 */
	public JavaArchive(JarFile jarFile) {
		super(jarFile);
	}

	@Override
	public void load() {
		Enumeration<JarEntry> entries = jarFile.entries();
		while (entries.hasMoreElements()) {
			JarEntry jarEntry = entries.nextElement();
			String name = jarEntry.getName();
			if (name.endsWith(".class")) {
				ClassNode classNode = new ClassNode();
				try {
					ClassReader classReader = new ClassReader(
							jarFile.getInputStream(jarEntry));
					classReader.accept(classNode, ClassReader.SKIP_FRAMES
							| ClassReader.SKIP_DEBUG);
					classes.put(classNode.name.replace(".class", ""), classNode);
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
