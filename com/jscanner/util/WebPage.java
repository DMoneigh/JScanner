package com.jscanner.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.jscanner.archive.Archive;
import com.jscanner.archive.impl.ClassArchive;
import com.jscanner.archive.impl.JavaArchive;

/**
 * Represents a web page.
 * 
 * @author Desmond Jackson
 */
public class WebPage extends Object {

	/**
	 * The HTML document.
	 */
	private Document document;

	/**
	 * Gets the HTML document from the url.
	 * 
	 * @param url The url to get the document from.
	 * 
	 * @throws IOException 
	 */
	public WebPage(String url) {
		super();
		try {
			this.document = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Attempts to get all archives from the HTML document.
	 * 
	 * @return All archives from the HTML document.
	 */
	public List<Archive> getArchives() {
		List<Archive> archives = new ArrayList<Archive>();
		for (Element appletElement : document.getElementsByTag("applet")) {
			String archiveAttribute = appletElement.attr("archive");
			int commaCount = 0;
			for (char character : archiveAttribute.toCharArray())
				if (character == ',')
					commaCount++;
			List<String> archiveAttributeList = new ArrayList<String>();
			for (int i = 0; i <= commaCount; i++)
				archiveAttributeList.add(archiveAttribute.split(",")[i].trim());
			String code = appletElement.attr("code");
			String codebase = appletElement.attr("codebase");
			if (!codebase.isEmpty()) {
				for (int i = 0; i < archiveAttributeList.size(); i++)
					archiveAttributeList.set(i, resolvePath(codebase,
							archiveAttributeList.get(i)));
				code = resolvePath(codebase, code);
			}
			File classFile = getURLClassFile(resolvePath(document.baseUri(),
					code));
			try {
				if (classFile != null) {
					archives.add(new ClassArchive(classFile));
					classFile.delete();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (String archive : archiveAttributeList) {
				try {
					JarFile jarFile = getURLJarFile(resolvePath(
							document.baseUri(), archive));
					if (jarFile != null)
						archives.add(new JavaArchive(jarFile));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return archives;
	}

	/**
	 * Resolves the path to a child directory from the parent directory. 
	 * 
	 * @param parentDirectory The root directory.
	 * 
	 * @param childDirectory The child directory.
	 * 
	 * @return The path to the child directory from the parent directory.
	 */
	private String resolvePath(String parentDirectory, String childDirectory) {
		try {
			return new URI(parentDirectory).resolve(childDirectory).toString();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets a class file from the specified url.
	 * 
	 * @param url The url to get the class file from.
	 * 
	 * @return A class file from the specified url.
	 */
	private File getURLClassFile(String url) {
		try {
			File classFile = new File("temp.class");
			URL u = new URL(url);
			InputStream inputStream = u.openStream();
			OutputStream outputStream = new FileOutputStream(classFile);
			byte[] bytes = new byte[1024];
			int length;
			while ((length = inputStream.read(bytes)) != -1)
				outputStream.write(bytes, 0, length);
			inputStream.close();
			outputStream.close();
			return classFile;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Gets a Jar file from the specified url.
	 * 
	 * @param url The url to get the Jar file from.
	 * 
	 * @return A Jar file from the specified url.
	 */
	private JarFile getURLJarFile(String url) {
		try {
			return ((JarURLConnection) new URL("jar:" + url + "!/")
			.openConnection()).getJarFile();
		} catch (IOException e) {
			return null;
		}
	}

}
