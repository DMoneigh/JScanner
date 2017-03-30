package net.jscanner.cli.command.impl;

import net.jscanner.cli.command.Command;

/**
 * The "quit" command.
 * 
 * @author Desmond Jackson
 */
public class QuitCommand extends Command {

	/**
	 * Creates a new "quit" command instance.
	 */
	public QuitCommand() {
		super("quit");
	}

	@Override
	public boolean execute(String... arguments) {
		System.exit(0);
		return true;
	}

	@Override
	public String getUsage() {
		return "quit";
	}

	@Override
	public String getHelp() {
		return "exits the program";
	}

}
