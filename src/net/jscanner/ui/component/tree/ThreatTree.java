package net.jscanner.ui.component.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import net.jscanner.ui.component.ComponentTree;
import net.jscanner.ui.component.ComponentTreeNode;
import net.jscanner.ui.component.treenode.ClassTreeNode;
import net.jscanner.ui.component.treenode.MethodTreeNode;
import net.jscanner.util.FileManager;

/**
 * The threat tree.
 * 
 * @author Desmond Jackson
 */
public class ThreatTree extends ComponentTree {

	/**
	 * The serial uid.
	 */
	private static final long serialVersionUID = 7979185806596621778L;

	/**
	 * Gets the user selected threats.
	 * 
	 * @return The user selected threats
	 */
	public Map<String, List<String>> getSelectThreats() {
		Map<String, List<String>> threats = new HashMap<String, List<String>>();
		String lastClass = null;
		if (!isSelectionEmpty()) {
			for (TreePath treePath : getSelectionPaths()) {
				ComponentTreeNode treeNode = (ComponentTreeNode) treePath.getLastPathComponent();
				if (treeNode instanceof ClassTreeNode) {
					String representation = (String) treeNode.getUserObject();
					if (lastClass == null || !lastClass.equals(representation))
						threats.put(lastClass = representation, new ArrayList<String>());
				} else if (treeNode instanceof MethodTreeNode) {
					String parentRepresentation = (String) ((ComponentTreeNode) treeNode.getParent()).getUserObject();
					String representation = (String) treeNode.getUserObject();
					if (lastClass == null || !lastClass.equals(parentRepresentation)) {
						threats.put(lastClass = parentRepresentation, new ArrayList<String>());
						threats.get(lastClass).add(representation);
					} else
						threats.get(lastClass).add(representation);
				}
			}
		}
		return threats;
	}

	@Override
	public TreeModel getTreeModel() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Threats");
		for (Entry<String, List<String>> entry : FileManager.getRuntimeClasses().entrySet())
			root.add(new ClassTreeNode(entry.getKey(), entry.getValue()));
		return new DefaultTreeModel(root);
	}

	@Override
	public int getCustomSelectionMode() {
		return TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION;
	}

}
