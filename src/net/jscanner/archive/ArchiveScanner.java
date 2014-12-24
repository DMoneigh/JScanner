package net.jscanner.archive;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import net.jscanner.threat.impl.ThreatClass;
import net.jscanner.threat.impl.ThreatMethod;

/**
 * Scans archives for threats.
 * 
 * @author Desmond Jackson
 */
public class ArchiveScanner extends Object {
	
	/**
	 * The archive to scan.
	 */
	private Archive archive;
	
	/**
	 * The threats to scan for.
	 */
	private List<ThreatClass> threatClasses;
	
	/**
	 * Prepares an archive for scanning.
	 * 
	 * @param archive The archive to scan
	 * 
	 * @param threatClasses The threats to scan for
	 */
	public ArchiveScanner(Archive archive, List<ThreatClass> threatClasses) {
		super();
		this.archive = archive;
		this.threatClasses = threatClasses;
		decompose();
	}
	
	/**
	 * Breaks the archive down to obtain necessary instructions.
	 */
	private void decompose() {
		for (ClassNode classNode : archive)
			for (Object node : classNode.methods) {
				MethodNode methodNode = (MethodNode) node;
				for (AbstractInsnNode abstractInsnNode :
					methodNode.instructions.toArray())
					if (abstractInsnNode instanceof MethodInsnNode)
						scan((MethodInsnNode) abstractInsnNode);
			}
	}
	
	/**
	 * Scans the method instruction node for threats.
	 * 
	 * @param methodInsnNode The method instruction node to scan
	 */
	private void scan(MethodInsnNode methodInsnNode) {
		for (ThreatClass threatClass : threatClasses) {
			if (threatClass.getName().equals(methodInsnNode.owner)) {
				threatClass.setUsed();
				if (methodInsnNode.name.equals("<init>"))
					addInteraction(threatClass, methodInsnNode);
				for (ThreatMethod threatMethod : threatClass.getThreatMethods())
					if (methodInsnNode.name.equals(threatMethod.getName())) {
						threatMethod.setUsed();
						addInteraction(threatClass, methodInsnNode);
					}
			}
		}
	}
	
	/**
	 * Appends an interaction to the list of interactions in a threat class if
	 * possible.
	 * 
	 * @param threatClass The threat class to add the interaction to
	 * 
	 * @param abstractInsnNode The abstract instruction node to derive the
	 * interaction from
	 */
	private void addInteraction(ThreatClass threatClass,
			AbstractInsnNode abstractInsnNode) {
		AbstractInsnNode previousNode = abstractInsnNode.getPrevious();
		if (previousNode instanceof VarInsnNode) {
			int var = ((VarInsnNode) previousNode).var;
			previousNode = previousNode.getPrevious();
			while (previousNode != null) {
				if (previousNode instanceof VarInsnNode) {
					VarInsnNode varInsnNode = (VarInsnNode) previousNode;
					if (varInsnNode.getOpcode() > 53 &&
							varInsnNode.getOpcode() < 87 &&
							varInsnNode.var == var) {
						previousNode = varInsnNode.getPrevious();
						break;
					}
				}
				previousNode = previousNode.getPrevious();
			}
		}
		if (previousNode instanceof LdcInsnNode)
			threatClass.addInteraction("Interaction: " + (
					(LdcInsnNode) previousNode).cst);
	}
	
	/**
	 * Gets the scans results.
	 * 
	 * @return The scan results.
	 */
	public List<String> getResults() {
		List<String> results = new ArrayList<String>();
		results.add("---------------------------------------------------------");
		results.add("Scanning: " + archive.getName());
		results.add("Time: " + new Date(System.currentTimeMillis()));
		results.add("\n");
		for (ThreatClass threatClass : threatClasses)
			if (threatClass.isUsed()) {
				results.add(threatClass.getOutput());
				for (ThreatMethod threatMethod : threatClass.getThreatMethods())
					if (threatMethod.isUsed())
						results.add(threatMethod.getOutput());
				for (String interaction : threatClass.getInteractions())
					results.add(interaction);
				results.add("");
			}
		results.add("\n");
		results.add("Scan Complete!");
		results.add("---------------------------------------------------------");
		return results;
	}
}
