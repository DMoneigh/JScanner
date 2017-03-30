package net.jscanner.cli;

import net.jscanner.cli.command.CommandHandler;

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
