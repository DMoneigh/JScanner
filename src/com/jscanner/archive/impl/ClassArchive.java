package com.jscanner.archive.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import com.jscanner.archive.Archive;

/**
 * Represents a class file.
 * 
 * @author Desmond Jackson
 */
public class ClassArchive extends Archive {

	/**
	 * Creates the class file representation.
	 * 
	 * @param file The class file to represent.
	 */
	public ClassArchive(File file) {
		super(file);
	}

	@Override
	public void load() {
		byte[] bytes = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bytes);
			fileInputStream.close();
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(bytes);
			classReader.accept(classNode, ClassReader.SKIP_FRAMES
					| ClassReader.SKIP_DEBUG);
			classes.put(classNode.name.replace(".class", ""), classNode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return file.getAbsolutePath();
	}

}
