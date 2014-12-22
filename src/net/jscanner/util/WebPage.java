package net.jscanner.util;

import java.io.File;
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

import net.jscanner.archive.Archive;
import net.jscanner.archive.impl.ClassFile;
import net.jscanner.archive.impl.JavaArchive;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

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
	 * Creates a new web page representation.
	 * 
	 * @param url The url of the web page
	 */
	public WebPage(String url) {
		super();
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Attempts to retrieve all archives from the HTML document.
	 * 
	 * @return All retrieved archives from the HTML document
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
					archives.add(new ClassFile(classFile));
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
	 * Resolves the path to a specified child directory form a specified home
	 * directory.
	 * 
	 * @param parentDirectory The specified parent directory
	 * 
	 * @param childDirectory The specified home directory
	 * 
	 * @return null if the child directory could not be resolved
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
	 * Retrieves a class file by specified url.
	 * 
	 * @param url The url to retrieve the class file from
	 * 
	 * @return null if the class file could not be retrieved
	 */
	private File getURLClassFile(String url) {
		File classFile = new File("temp.class");
		try {
			URL u = new URL(url);
			InputStream inputStream = u.openStream();
			OutputStream outputStream = new FileOutputStream(classFile);
			byte[] buffer = new byte[1024];
			int count;
			while ((count = inputStream.read(buffer)) != -1)
				outputStream.write(buffer, 0, count);
			outputStream.close();
			inputStream.close();
			return classFile;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Retrieves a Jar file by specified url.
	 * 
	 * @param url The url to retrieve the Jar file from
	 * 
	 * @return null if the Jar file could not be retrieved
	 */
	private JarFile getURLJarFile(String url) {
		try {
			return ((JarURLConnection) new URL("jar:" + url + "!/")
			.openConnection()).getJarFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
