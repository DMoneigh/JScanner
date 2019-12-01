package com.jscanner.ui.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jscanner.ui.UI;
import com.jscanner.ui.component.ComponentBuilder;
import com.jscanner.ui.component.textarea.LoggerTextArea;

/**
 * The JScanner user interface.
 * 
 * @author Desmond Jackson
 */
public class JScannerUI extends UI {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 5765856703623276447L;
	
	/**
	 * The logger.
	 */
	private LoggerTextArea logger;
	
	/**
	 * Creates a new user interface.
	 */
	public JScannerUI() {
		super("JScanner UI");
		setResizable(false);
	}
	
	/**
	 * Displays a new user interface.
	 */
	public static void display() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new JScannerUI().setVisible(true);
			}
			
		});
	}
	
	/**
	 * Gets the logger.
	 * 
	 * @return The logger
	 */
	public LoggerTextArea getLogger() {
		return logger;
	}
	
	@Override
	protected void addComponents() {
		setJMenuBar(ComponentBuilder.buildMenuBar());
		add(new JScrollPane(logger = new LoggerTextArea()), BorderLayout.CENTER);
	}
	
	@Override
	protected Dimension getDimension() {
		return new Dimension(425, 275);
	}
	
	/**
	 * Gets the user interface instance from the specified component.
	 * 
	 * @param component The specified component
	 * 
	 * @return null if the instance could not be obtained
	 */
	public static JScannerUI getInstance(Component component) {
		return (JScannerUI) UI.getInstance(component);
	}

}
