package com.jscanner.archive;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.jscanner.archive.util.Variable;

/**
 * The archive manager.
 * 
 * @author Desmond Jackson
 */
public class ArchiveManager {

	/**
	 * The archive modifier.
	 */
	private static ArchiveModifier modifier;

	/**
	 * The archive class loader.
	 */
	private static ArchiveClassLoader classLoader;

	/**
	 * Begins managing an archive.
	 * 
	 * @param archive The archive to manage
	 * 
	 * @return true if the archive can be successfully managed
	 */
	public static boolean manage(Archive archive) {
		modifier = new ArchiveModifier(archive);
		classLoader = new ArchiveClassLoader(archive);
		
		try {
			classLoader
			.loadClass(archive.getMainClassName(), true)
			.getDeclaredMethod("main", String[].class)
			.invoke(null, (Object) null);
			return true;
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		modifier = null;
		classLoader = null;
		return false;
	}

	/**
	 * Sets a variable value.
	 * 
	 * @param parent The parent class name
	 * 
	 * @param name The variable name
	 * 
	 * @param value The value
	 */
	public static void setVariableValue(String parent, String name, Object value) {
		for (Variable variable : modifier.getVariables())
			if (variable.getParentClassName().equals(parent) && variable.getName().equals(name)) {
				Class<?> mainClass = classLoader.getMainClass();
				String methodName = variable.isStatic() ? "setStaticVariableValue" : "setInstanceVariableValue";
				try {
					mainClass.getDeclaredMethod(methodName, String.class, String.class, Object.class)
					.invoke(null, parent, name, value);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException
						| SecurityException e) {
					e.printStackTrace();
				}
			}
	}

	/**
	 * Gets a variable value.
	 * 
	 * @param parent The parent class name
	 * 
	 * @param name The variable name
	 * 
	 * @return null if the variable value could not be obtained
	 */
	public static Object getVariableValue(String parent, String name) {
		for (Variable variable : modifier.getVariables())
			if (variable.getParentClassName().equals(parent) && variable.getName().equals(name)) {
				Class<?> mainClass = classLoader.getMainClass();
				String methodName = variable.isStatic() ? "getStaticVariableValue" : "getInstanceVariableValue";
				try {
					return mainClass.getDeclaredMethod(methodName, String.class, String.class)
							.invoke(null, parent, name);
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException |
						IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		return null;
	}
	
	/**
	 * Gets the variable parent class name and name that matches the  value.
	 * 
	 * @param value The value
	 * 
	 * @return null if no variable matches
	 */
	public static String findVariable(Object value) {
		for (Variable variable : modifier.getVariables())
			if (getVariableValue(variable.getParentClassName(), variable.getName()).equals(value))
				return variable.getParentClassName() + " " + variable.getName();
		return null;
	}

	/**
	 * Gets all usable variable signatures.
	 * 
	 * @return All usable variable signatures
	 */
	public static List<String> getVariableSignatures() {
		List<String> variableSignatures = new ArrayList<String>();
		for (Variable variable : modifier.getVariables())
			variableSignatures.add(variable.getParentClassName() + " " + variable.getName() + " " +
					variable.getType());
		return variableSignatures;
	}

	/**
	 * Checks if the archive manager is managing or not.
	 * 
	 * @return false if the archive manager is not managing
	 */
	public static boolean isManaging() {
		return classLoader != null && modifier != null;
	}
}
