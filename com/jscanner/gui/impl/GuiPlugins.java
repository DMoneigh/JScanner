package com.jscanner.gui.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;

import com.jscanner.gui.Gui;
import com.jscanner.gui.component.impl.ButtonAccept;
import com.jscanner.gui.component.impl.TreePlugins;

/**
 * The "Plugins" graphical user interface.
 * 
 * @author Desmond Jackson
 */
public class GuiPlugins extends Gui {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = 9066940816818346542L;

	/**
	 * Builds the "Plugins" graphical user interface.
	 */
	public GuiPlugins() {
		super("Plugins");
	}

	@Override
	public void addComponents() {
		TreePlugins treePlugins = new TreePlugins();
		add(new JScrollPane(treePlugins));
		add(new ButtonAccept(this, treePlugins), BorderLayout.SOUTH);
	}

	@Override
	public int getDefaultCloseOperation() {
		return DISPOSE_ON_CLOSE;
	}

	@Override
	public boolean getAlwaysOnTop() {
		return true;
	}
	
	@Override
	public boolean getResizable() {
		return false;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(400, 400);
	}

}
