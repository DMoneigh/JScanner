package com.jscanner.archive.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.jscanner.archive.Archive;

/**
 * Represents a class file.
 * 
 * @author Desmond Jackson
 */
public class ClassFile extends Archive {
	
	/**
	 * Creates a new class file representation.
	 * 
	 * @param classFile The class file to represent
	 */
	public ClassFile(File classFile) {
		super(classFile);
	}

	@Override
	protected void findClasses() {
		try {
			addClass(Files.readAllBytes(classFile.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return classFile.getAbsolutePath();
	}

}
