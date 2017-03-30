package net.jscanner.ui.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JScrollPane;

import net.jscanner.archive.Archive;
import net.jscanner.ui.UI;
import net.jscanner.ui.component.ComponentBuilder;
import net.jscanner.ui.component.button.ScanButton;
import net.jscanner.ui.component.tree.ThreatTree;

/**
 * The "Select Threats" user interface.
 * 
 * @author Desmond Jackson
 */
public class SelectThreatsUI extends UI implements WindowListener {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 7981425887061533055L;
	
	/**
	 * The parent user interface.
	 */
	private JScannerUI parent;
	
	/**
	 * The archive to scan.
	 */
	private Archive archive;
	
	/**
	 * The threat tree.
	 */
	private ThreatTree threatTree;
	
	/**
	 * Creates a new "Select Threats" user interface.
	 * 
	 * @param archive The archive to scan
	 * 
	 * @param parent The parent user interface
	 */
	public SelectThreatsUI(JScannerUI parent, Archive archive) {
		super("Select Threats");
		this.parent = parent;
		this.archive = archive;
		addWindowListener(this);
	}
	
	/**
	 * Gets the threat tree.
	 * 
	 * @return The threat tree
	 */
	public ThreatTree getThreatTree() {
		return threatTree;
	}
	
	/**
	 * Gets the archive to scan.
	 * 
	 * @return The archive to scan
	 */
	public Archive getArchive() {
		return archive;
	}
	
	/**
	 * Gets the parent user interface.
	 * 
	 * @return The parent user interface
	 */
	public JScannerUI getParent() {
		return parent;
	}
	
	/**
	 * Gets the user interface instance from the specified component.
	 * 
	 * @param component The specified component
	 * 
	 * @return null if the instance could not be obtained
	 */
	public static SelectThreatsUI getInstance(Component component) {
		return (SelectThreatsUI) UI.getInstance(component);
	}
	
	@Override
	protected void addComponents() {
		add(ComponentBuilder.buildSearchPanel(), BorderLayout.NORTH);
		add(new JScrollPane(threatTree = new ThreatTree()), BorderLayout.CENTER);
		add(new ScanButton(), BorderLayout.SOUTH);
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		parent.setVisible(false);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		parent.setVisible(true);
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	protected Dimension getDimension() {
		return new Dimension(500, 500);
	}

}
