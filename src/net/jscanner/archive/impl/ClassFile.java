package net.jscanner.archive.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import net.jscanner.archive.Archive;

/**
 * Represents a class file.
 * 
 * @author Desmond Jackson
 */
public class ClassFile extends Archive {

	/**
	 * Creates the class file representation.
	 * 
	 * @param classFile The class file to represent
	 */
	public ClassFile(File classFile) {
		super(classFile);
	}

	@Override
	public void findClasses() {
		try {
			InputStream inputStream = new FileInputStream(classFile);
			byte[] buffer = new byte[1024];
			int count;
			ByteArrayOutputStream byteArrayOutputStream =
					new ByteArrayOutputStream();
			while ((count = inputStream.read(buffer)) != -1)
				byteArrayOutputStream.write(buffer, 0 , count);
			inputStream.close();
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(
					byteArrayOutputStream.toByteArray());
			classReader.accept(classNode, ClassReader.SKIP_DEBUG |
					ClassReader.SKIP_FRAMES);
			classes.put(classNode.name.replace(".class", ""), classNode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return classFile.getAbsolutePath();
	}

}
