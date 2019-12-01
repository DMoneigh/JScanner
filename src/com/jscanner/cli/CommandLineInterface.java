package com.jscanner.cli;

import com.jscanner.cli.command.CommandHandler;

/**
 * Represents the command-line interface.
 * 
 * @author Desmond Jackson
 */
public class CommandLineInterface {
	
	/**
	 * Awaits for user input.
	 */
	public static void awaitInput() {
		new CommandHandler().start();
	}

}
