package com.jscanner.archive;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import com.jscanner.archive.util.Variable;
import com.jscanner.archive.util.VariableManager;

/***
 * Modified archives.
 * 
 * @author Desmond Jackson
 */
public class ArchiveModifier implements Opcodes {
	
	/**
	 * The archive to modify.
	 */
	private Archive archive;

	/**
	 * The list of LDC variables found in the archive.
	 */
	private List<Variable> variables = new ArrayList<Variable>();

	/**
	 * Creates the archive modifier.
	 * 
	 * @param archive The archive to modify
	 */
	@SuppressWarnings("unchecked")
	public ArchiveModifier(Archive archive) {
		this.archive = archive;
		archive.addClassNode(VariableManager.class);
		ClassNode mainClassNode = archive.getClassNode(archive.getMainClassName().replaceAll("\\.", "/"));
		
		mainClassNode.fields.add(getInstancesField());
		mainClassNode.methods.add(getClinitMethod());
		mainClassNode.methods.add(getSetInstanceVariableValueMethod());
		mainClassNode.methods.add(getSetStaticVariableValueMethod());
		mainClassNode.methods.add(getGetStaticVariableValueMethod());
		mainClassNode.methods.add(getGetInstanceVariableValueMethod());
		
		for (ClassNode node : archive) {
			for (Object fieldNodeObject : node.fields)
				if (fieldNodeObject instanceof FieldNode) {
					FieldNode fn = (FieldNode) fieldNodeObject;
					Type type = Type.getType(fn.desc);
					if (!Modifier.isFinal(fn.access) && type.equals(Type.INT_TYPE) ||
							type.equals(Type.getType(String.class))) {
						variables.add(new Variable(node.name.replaceAll("/", "\\."), fn.name, type,
								Modifier.isStatic(fn.access)));
						node.methods.add(getSetterMethod(node.name, fn, type));
						node.methods.add(getGetterMethod(node.name, fn, type));
					}
				}
			for (Object methodNodeObject : node.methods)
				if (methodNodeObject instanceof MethodNode) {
					MethodNode mn = (MethodNode) methodNodeObject;
					if (mn.name.equals("<init>"))
						for (AbstractInsnNode ain : mn.instructions.toArray())
							if (ain.getOpcode() == INVOKESPECIAL) {
								mn.instructions.insert(ain, getConstructorHook());
								break;
							}
				}
		}
	}
	
	/**
	 * Gets the constructor hook.
	 * 
	 * @return The constructor hook
	 */
	private InsnList getConstructorHook() {
		InsnList insns = new InsnList();
		insns.add(new FieldInsnNode(GETSTATIC, archive.getMainClassName().replaceAll("\\.", "/"), "INSTANCES",
				"Ljava/util/Map;"));
		insns.add(new VarInsnNode(ALOAD, 0));
		insns.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;",
				false));
		insns.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;",
				false));
		insns.add(new VarInsnNode(ALOAD, 0));
		insns.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/Map", "put",
				"(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true));
		insns.add(new InsnNode(POP));
		return insns;
	}

	/**
	 * Gets the getter method for the field node.
	 * 
	 * @param classNodeName The class node name 
	 * 
	 * @param fn The field node
	 * 
	 * @param type The field node type
	 * 
	 * @return The getter method for the field node
	 */
	private MethodNode getGetterMethod(String classNodeName, FieldNode fn, Type type) {
		boolean statik = Modifier.isStatic(fn.access);
		MethodNode getter = new MethodNode(statik ? ACC_PUBLIC + ACC_STATIC : ACC_PUBLIC, "jModifierGet" +
				fn.name, "()" + fn.desc, null, null);
		if (!statik)
			getter.instructions.add(new VarInsnNode(ALOAD, 0));
		getter.instructions.add(new FieldInsnNode(statik ? GETSTATIC : GETFIELD, classNodeName, fn.name,
				fn.desc));
		getter.instructions.add(new InsnNode(type.equals(Type.INT_TYPE) ? IRETURN : ARETURN));
		return getter;
	}

	/**
	 * Gets the setter method for the field node.
	 * 
	 * @param classNodeName The class node name 
	 * 
	 * @param fn The field node
	 * 
	 * @param type The field node type
	 * 
	 * @return The setter method for the field node
	 */
	private MethodNode getSetterMethod(String classNodeName, FieldNode fn, Type type) {
		boolean statik = Modifier.isStatic(fn.access);
		MethodNode setter = new MethodNode(statik ? ACC_PUBLIC + ACC_STATIC : ACC_PUBLIC, "jModifierSet" + 
				fn.name, "(" + fn.desc + ")V", null, null);
		if (statik)
			setter.instructions.add(new VarInsnNode(type.equals(Type.INT_TYPE) ? ILOAD : ALOAD, 0));
		else {
			setter.instructions.add(new VarInsnNode(ALOAD, 0));
			setter.instructions.add(new VarInsnNode(type.equals(Type.INT_TYPE) ? ILOAD : ALOAD, 1));
		}
		setter.instructions.add(new FieldInsnNode(statik ? PUTSTATIC : PUTFIELD,
				classNodeName, fn.name, fn.desc));
		setter.instructions.add(new InsnNode(RETURN));
		return setter;
	}

	/**
	 * Gets the "getInstanceVariableValue" method.
	 * 
	 * @return The "getInstanceVariableValue" method
	 */
	private MethodNode getGetInstanceVariableValueMethod() {
		MethodNode method = new MethodNode(ACC_PUBLIC + ACC_STATIC, "getInstanceVariableValue",
				"(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;", null, null);
		method.instructions.add(new VarInsnNode(ALOAD, 0));
		method.instructions.add(new VarInsnNode(ALOAD, 1));
		method.instructions.add(new FieldInsnNode(GETSTATIC, archive.getMainClassName().replaceAll("\\.", "/"),
				"INSTANCES", "Ljava/util/Map;"));
		method.instructions.add(new VarInsnNode(ALOAD, 0));
		method.instructions.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/Map", "get",
				"(Ljava/lang/Object;)Ljava/lang/Object;", true));
		method.instructions.add(new MethodInsnNode(INVOKESTATIC,
				VariableManager.class.getName().replaceAll("\\.", "/"), "getInstanceVariableValue",
				"(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", false));
		method.instructions.add(new InsnNode(ARETURN));
		return method;
	}

	/**
	 * Gets the "getStaticVariableValue" method.
	 * 
	 * @return The "getStaticVariableValue" method
	 */
	private MethodNode getGetStaticVariableValueMethod() {
		MethodNode method = new MethodNode(ACC_PUBLIC + ACC_STATIC, "getStaticVariableValue",
				"(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;", null, null);
		method.instructions.add(new VarInsnNode(ALOAD, 0));
		method.instructions.add(new VarInsnNode(ALOAD, 1));
		method.instructions.add(new MethodInsnNode(INVOKESTATIC,
				archive.getMainClassName().replaceAll("\\.", "/"), "getStaticVariableValue",
				"(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;", false));
		method.instructions.add(new InsnNode(ARETURN));
		return method;
	}

	/**
	 * Gets the "setStaticVariableValue" method.
	 * 
	 * @return The "setStaticVariableValue" method
	 */
	private MethodNode getSetStaticVariableValueMethod() {
		MethodNode method = new MethodNode(ACC_PUBLIC + ACC_STATIC, "setStaticVariableValue",
				"(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", null, null);
		method.instructions.add(new VarInsnNode(ALOAD, 0));
		method.instructions.add(new VarInsnNode(ALOAD, 1));
		method.instructions.add(new VarInsnNode(ALOAD, 2));
		method.instructions.add(new MethodInsnNode(INVOKESTATIC,
				VariableManager.class.getName().replaceAll("\\.", "/"), "setStaticVariableValue",
				"(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", false));
		method.instructions.add(new InsnNode(RETURN));
		return method;
	}

	/**
	 * Gets the "setInstanceVariableValue" method.
	 * 
	 * @return The "setInstanceVariableValue" method
	 */
	private MethodNode getSetInstanceVariableValueMethod() {
		MethodNode method = new MethodNode(ACC_PUBLIC + ACC_STATIC, "setInstanceVariableValue",
				"(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", null, null);
		method.instructions.add(new VarInsnNode(ALOAD, 0));
		method.instructions.add(new VarInsnNode(ALOAD, 1));
		method.instructions.add(new FieldInsnNode(GETSTATIC, archive.getMainClassName().replaceAll("\\.", "/"),
				"INSTANCES", "Ljava/util/Map;"));
		method.instructions.add(new VarInsnNode(ALOAD, 0));
		method.instructions.add(new MethodInsnNode(INVOKEINTERFACE, "java/util/Map", "get" ,
				"(Ljava/lang/Object;)Ljava/lang/Object;", true));
		method.instructions.add(new VarInsnNode(ALOAD, 2));
		method.instructions.add(new MethodInsnNode(INVOKESTATIC,
				VariableManager.class.getName().replaceAll("\\.", "/"), "setInstanceVariableValue",
				"(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V", false));
		method.instructions.add(new InsnNode(RETURN));
		return method;
	}

	//TODO Merge <clinit> methods in every class
	/**
	 * Gets the <clinit> method.
	 * 
	 * @return The <clinit> method
	 */
	private MethodNode getClinitMethod() {
		MethodNode clinit = new MethodNode(ACC_STATIC, "<clinit>", "()V", null, null);
		clinit.instructions.add(new TypeInsnNode(NEW, "java/util/HashMap"));
		clinit.instructions.add(new InsnNode(DUP));
		clinit.instructions.add(new MethodInsnNode(INVOKESPECIAL, "java/util/HashMap", "<init>", "()V", false));
		clinit.instructions.add(new FieldInsnNode(PUTSTATIC, archive.getMainClassName().replaceAll("\\.", "/"),
				"INSTANCES", "Ljava/util/Map;"));
		clinit.instructions.add(new InsnNode(RETURN));
		return clinit;
	}

	/**
	 * Gets the "INSTANCES" field.
	 * 
	 * @return The "INSTANCES" field
	 */
	private FieldNode getInstancesField() {
		return new FieldNode(ACC_PUBLIC + ACC_STATIC, "INSTANCES", "Ljava/util/Map;",
				"Ljava/lang/Map<Ljava/lang/String;Ljava/lang/Object;>;", null);
	}

	/**
	 * Gets the list of LDC variables found in the archive.
	 * 
	 * @return The list of LDC variables found in the archive
	 */
	public List<Variable> getVariables() {
		return variables;
	}

}
