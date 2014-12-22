package net.jscanner.gui.component.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;

import org.objectweb.asm.tree.ClassNode;

import net.jscanner.archive.Archive;
import net.jscanner.gui.component.ComponentTree;
import net.jscanner.util.FileManager;

/**
 * The "Threats" tree.
 * 
 * @author Desmond Jackson
 */
public class TreeThreats extends ComponentTree {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 6751702291029984950L;

	/**
	 * Creates a new "Threats" tree.
	 */
	public TreeThreats() {
		super();
	}

	@Override
	public TreeModel getTreeModel() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Threats");
		List<Archive> archives = new ArrayList<Archive>();
		archives.add(FileManager.getRuntimeArchive());
		archives.addAll(FileManager.getPlugins());
		for (Archive archive : archives)
			for (ClassNode classNode : archive)
				root.add(new TreeNodeThreat(classNode.name, classNode));
		return new DefaultTreeModel(root);
	}

	@Override
	public int getSelectionMode() {
		return TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION;
	}

}
