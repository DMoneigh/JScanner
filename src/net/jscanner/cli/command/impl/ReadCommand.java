package net.jscanner.cli.command.impl;

import java.util.List;
import java.util.regex.Pattern;

import net.jscanner.cli.command.Command;
import net.jscanner.cli.command.CommandHandler;
import net.jscanner.util.FileManager;

/**
 * The "read" command.
 * 
 * @author Desmond Jackson
 */
public class ReadCommand extends Command {

	/**
	 * The commmand handler.
	 */
	private CommandHandler commandHandler;

	/**
	 * Creates a new "read" command instance.
	 * 
	 * @param commandHandler The command handler
	 */
	public ReadCommand(CommandHandler commandHandler) {
		super("read");
		this.commandHandler = commandHandler;
	}

	@Override
	public boolean execute(String... arguments) {
		if (arguments.length > 0) {
			List<String> lines = FileManager.lines(arguments[0]);
			for (String line : lines)
				if (!line.trim().isEmpty()) {
					String name = line.split(" ")[0];
					if (!name.equals("read"))
						commandHandler.handleCommand(name, line.replaceFirst(Pattern.quote(name), "").trim().split(" "));
				}
			return true;
		}
		return false;
	}

	@Override
	public String getUsage() {
		return "read <file>";
	}

	@Override
	public String getHelp() {
		return "reads JScanner commands from the specified file";
	}

}
