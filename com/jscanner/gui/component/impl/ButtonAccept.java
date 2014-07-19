package com.jscanner.gui.component.impl;

import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.tree.TreePath;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import com.jscanner.JScanner;
import com.jscanner.archive.Archive;
import com.jscanner.archive.ArchiveScanner;
import com.jscanner.gui.component.ComponentButton;
import com.jscanner.gui.impl.GuiPlugins;

/**
 * The "Accept" button.
 * 
 * @author Desmond Jackson
 */
public class ButtonAccept extends ComponentButton {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = -1930170210990367352L;

	/**
	 * The "Plugins" graphical user interface.
	 */
	private GuiPlugins guiPlugins;

	/**
	 * The "Plugins" tree.
	 */
	private TreePlugins treePlugins;

	/**
	 * Builds the "Accept" button.
	 * 
	 * @param pluginsGui The "Plugins" graphical user interface.
	 * 
	 * @param treePlugins The "Plugins" tree.
	 */
	public ButtonAccept(GuiPlugins guiPlugins, TreePlugins treePlugins) {
		super("Accept");
		this.guiPlugins = guiPlugins;
		this.treePlugins = treePlugins;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Map<String, Boolean> pluginThreats = new TreeMap<String, Boolean>();
		for (TreePath treePath : treePlugins.getSelectionPaths()) {
			if (treePath.getPathCount() >= 3) {
				Archive archive = treePlugins.getArchive(treePath
						.getPathComponent(1).toString());
				for (ClassNode classNode : archive)
					if (classNode.name.equals(treePath.getPathComponent(2)
							.toString())) {
						pluginThreats.put(classNode.name, false);
						if (treePath.getPathCount() == 3) {
							for (Object object : classNode.methods)
								if (object instanceof MethodNode) {
									MethodNode methodNode = (MethodNode) object;
									pluginThreats.put(methodNode.name +
											methodNode.desc, false);
								}
						} else if (treePath.getPathCount() == 4) {
							for (Object object : classNode.methods)
								if (object instanceof MethodNode) {
									MethodNode methodNode = (MethodNode) object;
									if ((methodNode.name + methodNode.desc)
											.equals(treePath.getPathComponent(3)
													.toString()))
										pluginThreats.put(methodNode.name +
												methodNode.desc, false);
								}
						}
					}
			}
		}
		ArchiveScanner.pluginThreats = pluginThreats;
		JScanner.log("Successfully loaded plugins for scanning!");
		guiPlugins.dispose();
	}

}
