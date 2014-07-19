package com.jscanner;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jscanner.gui.Gui;
import com.jscanner.gui.component.impl.MenuBarJScanner;
import com.jscanner.gui.component.impl.TextAreaConsole;
import com.jscanner.util.FileManager;

/**
 * The main class.
 * 
 * @author Desmond Jackson
 */
public class JScanner extends Gui {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = -3732291235586480448L;

	/**
	 * The current version.
	 */
	private static final double VERSION = 1.2D;

	/**
	 * The console of JScanner.
	 */
	private static final TextAreaConsole CONSOLE = new TextAreaConsole();

	/**
	 * Builds the JScanner graphical user interface.
	 */
	public JScanner() {
		super("JScanner " + VERSION);
	}

	/**
	 * Creates directories and the graphical user interface.
	 * 
	 * @param args String arguments.
	 */
	public static void main(String[] args) {
		FileManager.createDirectories();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new JScanner().setVisible(true);
	}

	/**
	 * Writes text onto the console.
	 * 
	 * @param string The text to write onto the console.
	 */
	public static void log(String string) {
		CONSOLE.append(string);
	}

	/**
	 * Clears the text on the console.
	 */
	public static void clearLog() {
		CONSOLE.setText(null);
	}

	/**
	 * Gets the text from the console.
	 * 
	 * @return The text from the console.
	 */
	public static String getConsoleText() {
		return CONSOLE.getText();
	}

	@Override
	public void addComponents() {
		add(new MenuBarJScanner(), BorderLayout.NORTH);
		add(new JScrollPane(CONSOLE));
	}

	@Override
	public int getDefaultCloseOperation() {
		return EXIT_ON_CLOSE;
	}

	@Override
	public boolean getAlwaysOnTop() {
		return false;
	}
	
	@Override
	public boolean getResizable() {
		return true;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(400, 250);
	}

}
