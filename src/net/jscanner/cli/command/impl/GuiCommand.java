package net.jscanner.cli.command.impl;

import net.jscanner.cli.command.Command;
import net.jscanner.ui.impl.JScannerUI;

/**
 * The "gui" command.
 * 
 * @author Desmond Jackson
 */
public class GuiCommand extends Command {

	/**
	 * Creates a new "gui" command instance.
	 */
	public GuiCommand() {
		super("gui");
	}

	@Override
	public boolean execute(String... arguments) {
		JScannerUI.display();
		return true;
	}

	@Override
	public String getUsage() {
		return "gui";
	}

	@Override
	public String getHelp() {
		return "displays the user interface";
	}

}
