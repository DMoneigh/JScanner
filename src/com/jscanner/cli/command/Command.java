package com.jscanner.cli.command;

/**
 * Represents a command received by the command-line interface.
 * 
 * @author Desmond Jackson
 */
public abstract class Command {
	
	/**
	 * The command name.
	 */
	private String name;
	
	/**
	 * Creates a new command representation.
	 * 
	 * @param name The command name
	 */
	public Command(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the command name.
	 * 
	 * @return The command name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Executes a command with specified arguments.
	 * 
	 * @param arguments The arguments
	 * 
	 * @return true if command was successfully completed
	 */
	public abstract boolean execute(String... arguments);
	
	/**
	 * Gets the command usage.
	 * 
	 * @return The command usage
	 */
	public abstract String getUsage();
	
	/**
	 * Gets the command help.
	 * 
	 * @return The command help
	 */
	public abstract String getHelp();

}
