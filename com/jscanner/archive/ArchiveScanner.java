package com.jscanner.archive;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import com.jscanner.JScanner;
import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;
import com.jscanner.threat.ThreatObject;
import com.jscanner.threat.impl.CipherClass;
import com.jscanner.threat.impl.ClassLoaderClass;
import com.jscanner.threat.impl.DataInputStreamClass;
import com.jscanner.threat.impl.DataOutputStreamClass;
import com.jscanner.threat.impl.DatagramSocketClass;
import com.jscanner.threat.impl.FileClass;
import com.jscanner.threat.impl.FileReaderClass;
import com.jscanner.threat.impl.FileWriterClass;
import com.jscanner.threat.impl.InputStreamClass;
import com.jscanner.threat.impl.JarFileClass;
import com.jscanner.threat.impl.OutputStreamClass;
import com.jscanner.threat.impl.RuntimeClass;
import com.jscanner.threat.impl.ServerSocketClass;
import com.jscanner.threat.impl.SocketClass;
import com.jscanner.threat.impl.URLClass;
import com.jscanner.threat.impl.URLClassLoaderClass;

/**
 * Scans a user selected archive for threats.
 * 
 * @author Desmond Jackson
 */
public class ArchiveScanner extends Object implements Opcodes {

	/**
	 * The threats selected in the "Plugins" graphical user interface.
	 */
	public static Map<String, Boolean> pluginThreats =
			new TreeMap<String, Boolean>();

	/**
	 * The archive to scan.
	 */
	private Archive archive;

	/**
	 * A list of all the threatening classes.
	 */
	private List<Class<? extends ThreatClass>> threatClasses;

	/**
	 * Prepares the archive to be scanned.
	 * 
	 * @param archive The archive to scan.
	 */
	public ArchiveScanner(Archive archive) {
		super();
		this.archive = archive;
		this.threatClasses = new ArrayList<Class<? extends ThreatClass>>();
		loadThreatClasses();
		scan();
	}

	/**
	 * Loads all threatening classes into memory.
	 */
	private void loadThreatClasses() {
		threatClasses.add(CipherClass.class);
		threatClasses.add(ClassLoaderClass.class);
		threatClasses.add(DatagramSocketClass.class);
		threatClasses.add(DataInputStreamClass.class);
		threatClasses.add(DataOutputStreamClass.class);
		threatClasses.add(FileClass.class);
		threatClasses.add(FileReaderClass.class);
		threatClasses.add(FileWriterClass.class);
		threatClasses.add(InputStreamClass.class);
		threatClasses.add(JarFileClass.class);
		threatClasses.add(OutputStreamClass.class);
		threatClasses.add(RuntimeClass.class);
		threatClasses.add(ServerSocketClass.class);
		threatClasses.add(SocketClass.class);
		threatClasses.add(URLClass.class);
		threatClasses.add(URLClassLoaderClass.class);
	}

	/**
	 * Scans the archive for threats.
	 */
	private void scan() {
		JScanner.log("Scanning: " + archive.getName());
		JScanner.log("Time: " + new Date(System.currentTimeMillis()) + "\n");
		Map<ThreatClass, List<ThreatMethod>> threatMap = getThreatMap();
		for (ClassNode classNode : archive)
			for (Object object : classNode.methods)
				if (object instanceof MethodNode) {
					MethodNode methodNode = (MethodNode) object;
					for (AbstractInsnNode abstractInsnNode : methodNode.instructions.toArray())
						if (abstractInsnNode instanceof MethodInsnNode)
							scanMethodInsnNode((MethodInsnNode) abstractInsnNode, threatMap);
				}
		printScanResults(threatMap);
	}

	/**
	 * Gets a map of all the threatening classes' threatening methods.
	 * 
	 * @return A map of all the threatening classes' threatening methods.
	 */
	private Map<ThreatClass, List<ThreatMethod>> getThreatMap() {
		Map<ThreatClass, List<ThreatMethod>> threatMap = 
				new HashMap<ThreatClass, List<ThreatMethod>>();
		for (Class<? extends ThreatClass> threatClass : threatClasses)
			try {
				ThreatClass threatClassInstance = threatClass.newInstance();
				threatMap.put(threatClassInstance, threatClassInstance
						.getThreatMethodInstances());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		return threatMap;
	}

	/**
	 * Scans a method instruction node for threats.
	 * 
	 * @param methodInsnNode The method instruction node to scan.
	 * 
	 * @param threatMap The map of threat objects.
	 */
	private void scanMethodInsnNode(MethodInsnNode methodInsnNode,
			Map<ThreatClass, List<ThreatMethod>> threatMap) {
		String methodInsnNodeOwner = "L" + methodInsnNode.owner + ";";
		for (Entry<ThreatClass, List<ThreatMethod>> threatEntry : threatMap.entrySet()) {
			ThreatClass threatClassInstance = threatEntry.getKey();
			if (methodInsnNodeOwner.equals(threatClassInstance.getName())) {
				threatClassInstance.setUsed();
				addInteraction(threatClassInstance, methodInsnNode);
				for (ThreatMethod threatMethodInstance : threatEntry.getValue()) {
					if (methodInsnNode.name.equals(threatMethodInstance.getName())) {
						threatMethodInstance.setUsed();
						addInteraction(threatMethodInstance, methodInsnNode);
					}
				}
			}
		}
		for (Entry<String, Boolean> entry : pluginThreats.entrySet()) {
			if (methodInsnNode.owner.equals(entry.getKey())) {
				entry.setValue(true);
			}
			if ((methodInsnNode.name + methodInsnNode.desc)
					.equals(entry.getKey()))
				entry.setValue(true);
		}
	}

	/**
	 * Attempts to add an interaction to a threat object.
	 * 
	 * @param threatObject The threat object to add the interaction to.
	 * 
	 * @param abstractInsnNode The abstract instruction node to derive the
	 * interaction from.
	 */
	private void addInteraction(ThreatObject threatObject,
			AbstractInsnNode abstractInsnNode) {
		AbstractInsnNode previousNode = abstractInsnNode.getPrevious();
		if (previousNode instanceof VarInsnNode) {
			int var = ((VarInsnNode) previousNode).var;
			AbstractInsnNode nextNode = previousNode.getPrevious();
			while (nextNode != null) {
				if (nextNode instanceof VarInsnNode) {
					VarInsnNode	varInsnNode = (VarInsnNode) nextNode;
					if (varInsnNode.getOpcode() > 53 &&
							varInsnNode.getOpcode() < 87 &&
							varInsnNode.var == var) {
						previousNode = varInsnNode.getPrevious();
						break;
					}
				}
				nextNode = nextNode.getPrevious();
			}
		}
		if (previousNode instanceof LdcInsnNode) {
			LdcInsnNode ldcInsnNode = (LdcInsnNode) previousNode;
			threatObject.addInteraction("Interaction: " + ldcInsnNode.cst);
		}
	}

	/**
	 * Prints the results from the scanned archive.
	 * 
	 * @param threatMap The map of threat objects.
	 */
	private void printScanResults(Map<ThreatClass, List<ThreatMethod>> threatMap) {
		boolean regularThreatsFound = false;
		for (Entry<ThreatClass, List<ThreatMethod>> threatEntry : threatMap.entrySet()) {
			ThreatClass threatClassInstance = threatEntry.getKey();
			if (threatClassInstance.isUsed()) {
				regularThreatsFound = true;
				JScanner.log(threatClassInstance.getReply());
				printInteraction(threatClassInstance);
				for (ThreatMethod threatMethodInstance : threatEntry.getValue())
					if (threatMethodInstance.isUsed()) {
						JScanner.log(threatMethodInstance.getReply());
						printInteraction(threatMethodInstance);
					}
				JScanner.log("\n");
			}
		}
		if (!regularThreatsFound)
			JScanner.log("No regular threats found in this archive!\n");
		boolean pluginThreatsFound = false;
		for (Entry<String, Boolean> pluginEntry : pluginThreats.entrySet())
			if (pluginEntry.getValue()) {
				pluginThreatsFound = true;
				JScanner.log(pluginEntry.getKey() + " is used.");
			}
		if (!pluginThreatsFound)
			JScanner.log("No plugin threats found in this archive!\n");
	}

	/**
	 * Prints the interactions from a threat object.
	 * 
	 * @param threatObject The threat object to print from.
	 */
	private void printInteraction(ThreatObject threatObject) {
		for (String interaction : threatObject.getInteractions())
			JScanner.log(interaction);
	}

}