package net.jscanner.cli.command.impl;

import java.util.List;
import java.util.Map.Entry;

import net.jscanner.cli.command.Command;
import net.jscanner.util.FileManager;

/**
 * The "find" command.
 * 
 * @author Desmond Jackson
 */
public class FindCommand extends Command {

	/**
	 * Creates a new "find" command instance.
	 */
	public FindCommand() {
		super("find");
	}

	@Override
	public boolean execute(String... arguments) {
		if (arguments.length > 0) {
			String name = arguments[0];
			for (Entry<String, List<String>> entry : FileManager.getRuntimeClasses().entrySet()) {
				String key = entry.getKey();
				String className = key.substring(key.lastIndexOf("/") + 1);
				if (name.endsWith(".class") && className.equalsIgnoreCase(name.split(".class")[0]))
					System.out.println(key);
				else if (key.contains(name))
					System.out.println(key);
			}
			return true;
		}
		return false;
	}

	@Override
	public String getUsage() {
		return "find <package/class name> | <class name>.class";
	}

	@Override
	public String getHelp() {
		return "finds classes by name";
	}

}
