package com.jscanner.archive.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.IOUtils;

import com.jscanner.archive.Archive;

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
	
	/**
	 * Closes the underlying Jar file.
	 */
	public void close() {
		try {
			jarFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void findClasses() {
		try {
			Enumeration<JarEntry> entries = jarFile.entries();
			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				if (entry.getName().endsWith(".class"))
					addClass(IOUtils.toByteArray(jarFile.getInputStream(entry)));
				else
					try {
						InputStream in = jarFile.getInputStream(entry);
						byte[] bytes = new byte[1024];
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						int count = 0;
						while ((count = in.read(bytes)) != -1)
							baos.write(bytes, 0, count);
						in.close();
						otherFiles.put(entry.getName(), baos.toByteArray());
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
//			jarFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return jarFile.getName();
	}

}
