package com.jscanner.archive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.objectweb.asm.Handle;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import com.google.gson.Gson;
import com.tonicsystems.jarjar.asm.Opcodes;

/**
 * Scans archives for malicious bytecode instructions.
 * 
 * @author Desmond Jackson
 */
public class ArchiveScanner {

	/**
	 * The archive to scan.
	 */
	private Archive archive;

	/**
	 * The method instructions nodes found in the archive.
	 */
	private List<MethodInsnNode> methodInstructionNodes;

	/**
	 * Creates a new archive scanner.
	 * 
	 * @param archive The archive to scan
	 */
	public ArchiveScanner(Archive archive) {
		this.archive = archive;
		methodInstructionNodes = findMethodInstructionNodes();
	}

	/**
	 * Finds method instruction nodes in the archive.
	 * 
	 * @return The method instruction nodes found in the archive
	 */
	private List<MethodInsnNode> findMethodInstructionNodes() {
		List<MethodInsnNode> invocations = new ArrayList<MethodInsnNode>();
		for (ClassNode node : archive) for (Object object : node.methods.toArray())
			if (object instanceof MethodNode)
				for (AbstractInsnNode ain : ((MethodNode) object).instructions.toArray())
					if (ain instanceof MethodInsnNode)
						invocations.add((MethodInsnNode) ain);
					else if (ain instanceof InvokeDynamicInsnNode) {
						Handle handle = ((InvokeDynamicInsnNode) ain).bsm;
						invocations.add(new MethodInsnNode(Opcodes.INVOKEDYNAMIC, handle.getOwner(), handle.getName(), handle.getDesc(), handle.isInterface()));
					}
		return invocations;
	}

	/**
	 * Scans the archive for the specified class map of threats.
	 * 
	 * @param threats A map of threatening classes and their methods
	 * 
	 * @return The threats found in the archive in JSON format
	 */
	public String scan(Map<String, List<String>> threats) {
		Map<String, List<String>> found = new HashMap<String, List<String>>();
		for (MethodInsnNode min : methodInstructionNodes) {
			String clazz = min.owner; String method = min.name;
			for (Entry<String, List<String>> entry : threats.entrySet())
				if (entry.getKey().equals(clazz)) {
					if (!found.containsKey(clazz))
						found.put(clazz, new ArrayList<String>());
					if (entry.getValue().contains(method) && !found.get(clazz).contains(method))
						found.get(clazz).add(method);
				}
		}
		return new Gson().toJson(found);
	}

}
