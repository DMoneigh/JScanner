package com.jscanner.ui.component;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import com.jscanner.ui.component.menu.EditMenu;
import com.jscanner.ui.component.menu.FileMenu;
import com.jscanner.ui.component.menu.ToolsMenu;
import com.jscanner.ui.component.menu.WindowMenu;
import com.jscanner.ui.component.textfield.SearchTextField;

/**
 * Builds components for the user interface.
 * 
 * @author Desmond Jackson
 */
public class ComponentBuilder {
	
	/**
	 * Builds the menu bar.
	 * 
	 * @return The built menu bar
	 */
	public static JMenuBar buildMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(new FileMenu());
		menuBar.add(new EditMenu());
		menuBar.add(new ToolsMenu());
		menuBar.add(new WindowMenu());
		return menuBar;
	}
	
	/**
	 * Builds the search panel.
	 * 
	 * @return The built search panel
	 */
	public static JPanel buildSearchPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 1;
		panel.add(new JLabel("Search: "), gbc);
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new SearchTextField(), gbc);
		return panel;
	}
	
}
