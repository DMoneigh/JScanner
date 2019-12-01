package com.jscanner.cli.command;

import java.util.Scanner;
import java.util.regex.Pattern;

import com.jscanner.cli.command.impl.DateCommand;
import com.jscanner.cli.command.impl.FindCommand;
import com.jscanner.cli.command.impl.GuiCommand;
import com.jscanner.cli.command.impl.InjectCommand;
import com.jscanner.cli.command.impl.ListCommand;
import com.jscanner.cli.command.impl.QuitCommand;
import com.jscanner.cli.command.impl.ReadCommand;
import com.jscanner.cli.command.impl.ScanCommand;

/**
 * Handles commands received from the command-line interface.
 * 
 * @author Desmond Jackson
 */
public class CommandHandler extends Thread {

	/**
	 * An array of available commands.
	 */
	private final Command[] commands = new Command[] {
			new DateCommand(), new FindCommand(), new GuiCommand(),
			new ListCommand(), new QuitCommand(), new InjectCommand(),
			new ReadCommand(this), new ScanCommand()
	};

	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String name = line.split(" ")[0];
			handleCommand(name, line.replaceFirst(Pattern.quote(name), "").trim().split(" "));
		}
		scanner.close();
		System.exit(0);
	}

	/**
	 * Handles a received command.
	 * 
	 * @param name The command name
	 * 
	 * @param arguments The command arguments
	 */
	public void handleCommand(String name, String... arguments) {
		if (name.equalsIgnoreCase("help") || name.equals("?") || name.equalsIgnoreCase("commands")) {
			for (Command command : commands)
				System.out.println(command.getName() + " - " + command.getHelp());
			return;
		}
		for (Command command : commands)
			if (command.getName().equalsIgnoreCase(name)) {
				if (!command.execute(arguments))
					System.out.println("Usage - " + command.getUsage());
				return;
			}
		System.out.println("Unknown command... Type \"help\" for help.");
	}

}
