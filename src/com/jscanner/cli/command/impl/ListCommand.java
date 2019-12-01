package com.jscanner.cli.command.impl;

import java.util.List;
import java.util.Map.Entry;

import com.jscanner.cli.command.Command;
import com.jscanner.util.FileManager;

/**
 * The "list" command.
 * 
 * @author Desmond Jackson
 */
public class ListCommand extends Command {

	public ListCommand() {
		super("list");
	}

	@Override
	public boolean execute(String... arguments) {
		if (arguments.length > 0) {
			String name = arguments[0];
			for (Entry<String, List<String>> entry : FileManager.getRuntimeClasses().entrySet())
				if (entry.getKey().equalsIgnoreCase(name))
					for (String method : entry.getValue())
						System.out.println(method);
			return true;
		}
		return false;
	}

	@Override
	public String getUsage() {
		return "list <package/class name>";
	}

	@Override
	public String getHelp() {
		return "lists the children methods of the specified class";
	}

}
