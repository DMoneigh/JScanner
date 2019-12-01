package com.jscanner.ui.component.textfield;

import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

import com.jscanner.ui.component.ComponentTextField;
import com.jscanner.ui.component.tree.ThreatTree;
import com.jscanner.ui.impl.SelectThreatsUI;

/**
 * The search text field.
 * 
 * @author Desmond Jackson
 */
public class SearchTextField extends ComponentTextField {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = -2855961410568347755L;

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = getText();
		ThreatTree threatTree = SelectThreatsUI.getInstance(this).getThreatTree();
		Enumeration<?> children = ((DefaultMutableTreeNode) threatTree.getTreeModel().getRoot()).children();
		while (children.hasMoreElements()) {
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
			String name = child.getUserObject().toString();
			if (text.endsWith(".class")) {
				String clazz = text.split(".class")[0];
				if (name.endsWith("/" + clazz)) {
					threatTree.scrollRowToVisible(child.getRoot().getIndex(child) + 15);
					return;
				}
			} else if (name.contains(text)) {
				threatTree.scrollRowToVisible(child.getRoot().getIndex(child) + 15);
				return;
			}
		}
	}

}
