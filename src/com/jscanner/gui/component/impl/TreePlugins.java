package com.jscanner.gui.component.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import com.jscanner.archive.Archive;
import com.jscanner.gui.component.ComponentTree;
import com.jscanner.util.FileManager;

/**
 * The "Plugins" tree.
 * 
 * @author Desmond Jackson
 */
public class TreePlugins extends ComponentTree {

	/**
	 * Randomly generated serial number.
	 */
	private static final long serialVersionUID = 5799774088459837603L;
	
	/**
	 * The archives located in the "plugins" directory.
	 */
	private List<Archive> archives;
	
	/**
	 * Builds the "Plugins" tree.
	 */
	public TreePlugins() {
		super();
	}
	
	/**
	 * Gets an archive by its name.
	 * 
	 * @param name The name of the archive.
	 * 
	 * @return An archive by its name.
	 */
	public Archive getArchive(String name) {
		for (Archive archive : archives)
			if (archive.getName().endsWith(name))
				return archive;
		return null;
	}

	@Override
	public TreeModel createTreeModel() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Plugins");
		archives = new ArrayList<Archive>();
		for (Archive archive : FileManager.getPlugins()) {
			archives.add(archive);
			DefaultMutableTreeNode archivePath = new DefaultMutableTreeNode(
					archive.getName());
			for (ClassNode classNode : archive) {
				DefaultMutableTreeNode classNodeName =
						new DefaultMutableTreeNode(classNode.name);
				for (Object object : classNode.methods)
					if (object instanceof MethodNode) {
						MethodNode methodNode = (MethodNode) object;
						classNodeName.add(new DefaultMutableTreeNode(
								methodNode.name + methodNode.desc));
					}
				archivePath.add(classNodeName);
			}
			root.add(archivePath);
		}
		return new DefaultTreeModel(root);
	}
	
}
