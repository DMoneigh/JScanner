package com.jscanner.cli.command.impl;

import java.util.Date;

import com.jscanner.cli.command.Command;

/**
 * The "date" command.
 * 
 * @author Desmond Jackson
 */
public class DateCommand extends Command {

	/**
	 * Creates a new "date" command instance.
	 */
	public DateCommand() {
		super("date");
	}

	@Override
	public boolean execute(String... arguments) {
		System.out.println("System Date: " + new Date(System.currentTimeMillis()));
		return true;
	}

	@Override
	public String getUsage() {
		return "time";
	}

	@Override
	public String getHelp() {
		return "displays system date";
	}

}
