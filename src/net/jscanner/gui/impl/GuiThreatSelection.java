package net.jscanner.gui.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import net.jscanner.JScanner;
import net.jscanner.gui.Gui;
import net.jscanner.gui.component.ComponentMenuItem;
import net.jscanner.gui.component.impl.ButtonAccept;
import net.jscanner.gui.component.impl.TextFieldSearch;
import net.jscanner.gui.component.impl.TreeNodeThreat;
import net.jscanner.gui.component.impl.TreeThreats;
import net.jscanner.threat.impl.ThreatClass;
import net.jscanner.threat.impl.ThreatMethod;

/**
 * The "Threat Selection" graphical user interface.
 * 
 * @author Desmond Jackson
 */
public class GuiThreatSelection extends Gui implements WindowListener {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -4286629517926182646L;

	/**
	 * The "Threats" tree.
	 */
	private TreeThreats treeThreats;

	/**
	 * The JScanner instance that invoked this graphical user interface.
	 */
	private JScanner jScanner;

	/**
	 * The menu item that invoked this graphical user interface.
	 */
	private ComponentMenuItem componentMenuItem;

	/**
	 * Creates a new "Threat Selection" graphical user interface.
	 * 
	 * @param jScanner The JScanner instance that invoked this graphical
	 * user interface
	 * 
	 * @param componentMenuItem The menu item that invoked this graphical
	 * user interface 
	 */
	public GuiThreatSelection(JScanner jScanner,
			ComponentMenuItem componentMenuItem) {
		super("Threat Selection");
		addWindowListener(this);
		this.jScanner = jScanner;
		this.componentMenuItem = componentMenuItem;
	}

	/**
	 * Searches for a tree threat by name.
	 * 
	 * @param name The name of the tree threat to search for
	 */
	public void search(String name) {
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)
				treeThreats.getModel().getRoot();
		Enumeration<?> children = root.children();
		while (children.hasMoreElements()) {
			DefaultMutableTreeNode child = (DefaultMutableTreeNode)
					children.nextElement();
			if (child.getUserObject().toString().toLowerCase().contains(
					name.toLowerCase())) {
				int row = root.getIndex(child) + 1;
				treeThreats.scrollRowToVisible(row);
				return;
			}

		}
	}

	/**
	 * Gets the user selected threats.
	 * 
	 * @return The user selected threats
	 */
	public List<ThreatClass> getSelectedThreats() {
		List<ThreatClass> selectedThreats = new ArrayList<ThreatClass>();
		if (!treeThreats.isSelectionEmpty())
			for (TreePath treePath : treeThreats.getSelectionPaths()) {
				TreeNodeThreat treeNodeThreat = (TreeNodeThreat)
						treePath.getLastPathComponent();
				Object representation = treeNodeThreat.getRepresentation();
				ThreatClass lastThreatClass = getLastThreatClass(selectedThreats);
				if (representation instanceof ClassNode) {
					ClassNode classNode = (ClassNode) representation;
					if (lastThreatClass == null ||
							lastThreatClass.getRepresentation() != classNode)
						selectedThreats.add(new ThreatClass(classNode));
				} else {
					MethodNode methodNode = (MethodNode) representation;
					ClassNode classNode = (ClassNode) ((TreeNodeThreat) 
							treeNodeThreat.getParent()).getRepresentation();
					if (lastThreatClass == null ||
							lastThreatClass.getRepresentation() != classNode) {
						ThreatClass threatClass = new ThreatClass(classNode);
						threatClass.addThreatMethod(new ThreatMethod(methodNode));
						selectedThreats.add(threatClass);
					} else
						lastThreatClass.addThreatMethod(new ThreatMethod(
								methodNode));
				}
			}
		return selectedThreats;
	}

	/**
	 * Gets the last threat class of a threat class list.
	 * 
	 * @param threatClassList The threat class list
	 * 
	 * @return The last threat class from the threat class list
	 */
	private ThreatClass getLastThreatClass(List<ThreatClass> threatClassList) {
		if (threatClassList.size() < 1)
			return null;
		else
			return threatClassList.get(threatClassList.size() - 1);
	}

	/**
	 * Gets the GuiThreatSelection from a specified component.
	 * 
	 * @param component The component to get the GuiThreatSelection instance
	 * from
	 * 
	 * @return null if the instance could not be obtained
	 */
	public static GuiThreatSelection getInstance(Component component) {
		Container parent = component.getParent();
		while (parent != null)
			if (parent instanceof GuiThreatSelection)
				return (GuiThreatSelection) parent;
			else
				parent = parent.getParent();
		return null;
	}

	/**
	 * Gets the JScanner instance that invoked this graphical user interface.
	 * 
	 * @return The JScanner instance that invoked this graphical user interface
	 */
	public JScanner getJScannerInstance() {
		return jScanner;
	}

	/**
	 * Gets the menu item that invoked this graphical user interface.
	 * 
	 * @return The menu item that invoked this graphical user interface
	 */
	public ComponentMenuItem getComponentMenuItem() {
		return componentMenuItem;
	}

	@Override
	public void addComponents() {
		add(new TextFieldSearch(), BorderLayout.NORTH);
		treeThreats = new TreeThreats();
		add(new JScrollPane(treeThreats), BorderLayout.CENTER);
		add(new ButtonAccept(), BorderLayout.SOUTH);
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
	public int getDefaultCloseOperation() {
		return DISPOSE_ON_CLOSE;
	}

	@Override
	public Dimension getSize() {
		return new Dimension(500, 500);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		jScanner.setEnabled(false);
	}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {
		jScanner.setEnabled(true);
	}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

}
