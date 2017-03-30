package net.jscanner;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Joiner;

import net.jscanner.cli.CommandLineInterface;
import net.jscanner.server.WebServer;
import net.jscanner.ui.impl.JScannerUI;
import net.jscanner.util.Configuration;
import net.jscanner.util.FileManager;

/**
 * A Java Malware Defense Tool. 
 * 
 * @author Desmond Jackson
 */
public class JScanner {

	/**
	 * The main method.
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		FileManager.createDirectories();
		if (args.length > 0) handleArguments(Arrays.asList(args));
		WebServer.start(Configuration.WEB_SERVER_PORT);
		CommandLineInterface.awaitInput();
	}

	/**
	 * Handles the arguments passed from the command line.
	 * 
	 * @param args command line arguments
	 */
	private static void handleArguments(List<String> args) {
		String portFlag = "-p";
		String guiFlag = "-gui";
		if (!args.contains(guiFlag) && !args.contains(portFlag)) {
			System.setIn(new ByteArrayInputStream(Joiner.on(' ').join(args).getBytes(Charset.defaultCharset())));
			return;
		}
		if (args.contains(guiFlag))
			JScannerUI.display();
		if (args.contains(portFlag)) {
			int portIndex = args.indexOf(portFlag) + 1;
			if (portIndex < args.size())
				try {
					Configuration.WEB_SERVER_PORT = Integer.parseInt(args.get(portIndex));
				} catch (NumberFormatException e) {
					System.out.println("Not a real port number... using 4545");
				}
			else
				System.out.println("Specifiy a port number... using 4545");
		}
	}

}
