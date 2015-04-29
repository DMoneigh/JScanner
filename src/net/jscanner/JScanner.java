package net.jscanner;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.jscanner.gui.Gui;
import net.jscanner.gui.component.impl.MenuBarJScanner;
import net.jscanner.gui.component.impl.TextAreaConsole;
import net.jscanner.util.FileManager;

/**
 * The main class.
 * 
 * @author Desmond Jackson
 */
public class JScanner extends Gui {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 27601732538672048L;

	/**
	 * The version of the project.
	 */
	private static final double VERSION = 1.3D;

	/**
	 * The JScanner console.
	 */
	private TextAreaConsole console;

	/**
	 * Creates the JScanner graphical user interface.
	 */
	public JScanner() {
		super("JScanner " + VERSION);
	}

	/**
	 * The main method.
	 * 
	 * @param args String arguments passed to this application
	 */
	public static void main(String[] args) {
		FileManager.createDirectories();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new JScanner().setVisible(true);
	}

	/**
	 * Prints text onto the console.
	 * 
	 * @param string The text to print onto the console
	 */
	public void print(String string) {
		try {
			console.append(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prints a list of text onto the console.
	 * 
	 * @param strings The list of text
	 */
	public void print(List<String> strings) {
		for (String string : strings)
			print(string);
	}

	/**
	 * Clears the console.
	 */
	public void clearConsole() {
		console.setText(null);
	}

	/**
	 * Opens the user's default browser to the specified url.
	 * 
	 * @param url The specified url
	 */
	public static void browse(String url) {
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the text from the console.
	 * 
	 * @return The text from the console
	 */
	public String getConsoleText() {
		return console.getText();
	}

	/**
	 * Gets the JScanner instance from a specified component.
	 * 
	 * @param component The component to get the JScanner instance from
	 * 
	 * @return null if the instance could not be obtained
	 */
	public static JScanner getInstance(Component component) {
		Container parent = component.getParent();
		if (parent instanceof JPopupMenu)
			return getInstance(((JPopupMenu) parent).getInvoker());
		while (parent != null) {
			if (parent instanceof JScanner)
				return (JScanner) parent;
			parent = parent.getParent();
		}
		return null;
	}

	@Override
	public void addComponents() {
		add(new MenuBarJScanner(), BorderLayout.NORTH);
		console = new TextAreaConsole();
		add(new JScrollPane(console), BorderLayout.CENTER);
	}

	@Override
	public boolean getAlwaysOnTop() {
		return false;
	}

	@Override
	public boolean getResizable() {
		return false;
	}

	@Override
	public int getDefaultCloseOperation() {
		return DISPOSE_ON_CLOSE;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(425, 275);
	}
}
