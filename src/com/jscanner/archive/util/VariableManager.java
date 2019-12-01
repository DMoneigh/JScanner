package com.jscanner.archive.util;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * The variable manager.
 * 
 * @author Desmond Jackson
 */
public class VariableManager {
	
	/**
	 * Sets the value of an instance variable.
	 * 
	 * @param className The class name
	 * 
	 * @param methodName The method name
	 * 
	 * @param instance The instance
	 * 
	 * @param value The value
	 */
	public static void setInstanceVariableValue(String className, String methodName, Object instance,
			Object value) {
		try {
			Class<?> clazz = Class.forName(className);
			for (Method method : clazz.getDeclaredMethods())
				if (method.getName().equals("jModifierSet" + methodName))
					method.invoke(instance, value);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the value of a static variable.
	 * 
	 * @param className The class name
	 * 
	 * @param methodName The method name
	 * 
	 * @param value The value
	 */
	public static void setStaticVariableValue(String className, String methodName, Object value) {
		try {
			Class<?> clazz = Class.forName(className);
			for (Method method : clazz.getDeclaredMethods())
				if (method.getName().equals("jModifierSet" + methodName))
					method.invoke(null, value);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets a static variable value.
	 * 
	 * @param className The class name
	 * 
	 * @param methodName The method name
	 * 
	 * @return The static variable value
	 */
	public static Object getStaticVariableValue(String className, String methodName) {
		try {
			Class<?> clazz = Class.forName(className);
			for (Method method : clazz.getDeclaredMethods())
				if (method.getName().equals("jModifierGet" + methodName))
					return method.invoke(null);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Gets an instance variable value.
	 * 
	 * @param className The class name
	 * 
	 * @param methodName The method name
	 * 
	 * @param instance The instance
	 * 
	 * @return The instance variable value
	 */
	public static Object getInstanceVariableValue(String className, String methodName, Object instance) {
		try {
			Class<?> clazz = Class.forName(className);
			for (Method method : clazz.getDeclaredMethods()) {
				if (method.getName().equals("jModifierGet" + methodName))
					return method.invoke(instance);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
	