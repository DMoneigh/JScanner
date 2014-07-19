package com.jscanner.archive;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.jscanner.JScanner;

/**
 * Finds archives on the user's computer.
 * 
 * @author Desmond Jackson
 */
public class ArchiveFinder extends Object {

	/**
	 * Finds and displays the paths to all archives on the user's computer.
	 * @throws IOException 
	 */
	public static void findArchives() throws IOException {
		long startTime = System.currentTimeMillis();
		for (File directory : File.listRoots())
			for (File file : FileUtils.listFiles(directory,
					new String[] {"class", "jar"}, true)) {
				JScanner.log(file.getAbsolutePath());
			}
		JScanner.log("Scan took " 
				+ (System.currentTimeMillis() - startTime) / 1000 + " seconds!");
	}

}
