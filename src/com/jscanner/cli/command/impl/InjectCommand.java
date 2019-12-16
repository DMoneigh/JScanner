package com.jscanner.cli.command.impl;

import java.io.IOException;
import java.util.jar.JarFile;

import com.jscanner.archive.ArchiveManager;
import com.jscanner.archive.impl.JavaArchive;
import com.jscanner.cli.command.Command;

/**
 * The "inject" command.
 * 
 * @author Desmond Jackson
 */
public class InjectCommand extends Command {

	/**
	 * True if injection was successful.
	 */
	private boolean injected = false;

	/**
	 * The jar file.
	 */
	private JavaArchive jar;

	/**
	 * Creates a new "inject" command instance.
	 */
	public InjectCommand() {
		super("inject");
	}

	@Override
	public boolean execute(String... arguments) {
		if (arguments.length > 0) {

			if (!injected) {
				String path = arguments[0];

				if (path.endsWith(".class"))
					System.out.println("Inject only works with Java Archives (jar files).");
				else if (path.endsWith(".jar")) {
					try {
						jar = new JavaArchive(new JarFile(path));

						if (ArchiveManager.manage(jar))	{
							System.out.println(jar.getName() + " was successfully modified!");
							injected = true;
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {

				switch (arguments[0]) {

				case "kill" :

					injected = false;
					jar.close();

					System.out.println("Injection cleaned!");

					break;

				case "signatures" :

					for (String s : ArchiveManager.getVariableSignatures())
						System.out.println(s);

					break;

				case "set" :

					if (arguments.length >= 3 && arguments[1] != null && arguments[2] != null && arguments[3] != null && arguments[4] != null) {
						String type = arguments[3];

						if (type.equalsIgnoreCase("integer"))
							ArchiveManager.setVariableValue(arguments[1], arguments[2], Integer.parseInt(arguments[4]));
						else if (type.equalsIgnoreCase("double"))
							ArchiveManager.setVariableValue(arguments[1], arguments[2], Double.parseDouble(arguments[4]));
						else if (type.equalsIgnoreCase("float"))
							ArchiveManager.setVariableValue(arguments[1], arguments[2], Float.parseFloat(arguments[4]));
						else if (type.equalsIgnoreCase("boolean"))
							ArchiveManager.setVariableValue(arguments[1], arguments[2], Boolean.parseBoolean(arguments[4]));
						else if (type.equalsIgnoreCase("character"))
							ArchiveManager.setVariableValue(arguments[1], arguments[2], arguments[4].charAt(0));
						else
							ArchiveManager.setVariableValue(arguments[1], arguments[2], arguments[4]);
						
						System.out.println("Successfully set the value of " + arguments[2] + " to " + arguments[4]);
					}

					break;

				case "get" :

					if (arguments.length >= 2 && arguments[1] != null && arguments[2] != null)
						System.out.println(ArchiveManager.getVariableValue(arguments[1], arguments[2]));

				default :
					break;
				}
			}

			return true;
		}
		return false;
	}

	@Override
	public String getUsage() {
		return "inject <path to archive>";
	}

	@Override
	public String getHelp() {
		return "injects and hooks variables";
	}


}
