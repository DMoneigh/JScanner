package com.jscanner.server.handler.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;
import java.util.jar.JarFile;

import com.jscanner.archive.Archive;
import com.jscanner.archive.ArchiveScanner;
import com.jscanner.archive.impl.ClassFile;
import com.jscanner.archive.impl.JavaArchive;
import com.jscanner.server.handler.RequestHandler;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

/**
 * Represents the "/scan" directory on the web server.
 * 
 * @author Desmond Jackson
 */
public class ScanHandler extends RequestHandler {
	
	@Override
	protected String execute() {
		Archive archive = getArchive();
		String results = new ArchiveScanner(archive).scan(postData);
		
		if (archive instanceof JavaArchive)
			((JavaArchive) archive).close();
		
		return results;
	}
	
	/**
	 * Gets the archive posted to the "/scan" directory.
	 * 
	 * @return The archive posed to the "/scan" directory
	 */
	private Archive getArchive() {
		try {
			byte[] data = Base64.getDecoder().decode(postData.get("base64").get(0));
			String suffix = Magic.getMagicMatch(data).getMimeType()
					.equalsIgnoreCase("application/zip") ? "jar" : "class";
			File file = File.createTempFile(new Random().nextInt() + "", suffix);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data);
			fos.close();
			return suffix.equalsIgnoreCase("jar")
					? new JavaArchive(new JarFile(file)) : new ClassFile(file);
		} catch (MagicParseException e) {
			e.printStackTrace();
		} catch (MagicMatchNotFoundException e) {
			e.printStackTrace();
		} catch (MagicException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
