package com.jscanner.archive.util;

import org.objectweb.asm.Type;

/**
 * Represents a variable.
 * 
 * @author Desmond Jackson
 */
public class Variable {
	
	/**
	 * The parent class name.
	 */
	private String parentClassName;
	
	/**
	 * The variable name.
	 */
	private String name;
	
	/**
	 * The variable type.
	 */
	private Type type;
	
	/**
	 * The variable static state.
	 */
	private boolean isStatic;
	
	/**
	 * Creates the variable.
	 * 
	 * @param parentClassName The parent class name
	 * 
	 * @param name The variable name
	 * 
	 * @param type The variable type
	 * 
	 * @param isStatic The variable static state
	 */
	public Variable(String parentClassName, String name, Type type, boolean isStatic) {
		super();
		this.parentClassName = parentClassName;
		this.name = name;
		this.type = type;
		this.isStatic = isStatic;
	}
	
	/**
	 * Gets the variable static state.
	 * 
	 * @return The variable static state
	 */
	public boolean isStatic() {
		return isStatic;
	}
	
	/**
	 * Gets the variable type.
	 * 
	 * @return The variable type
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * Gets the variable name.
	 * 
	 * @return The variable name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the parent class name.
	 * 
	 * @return The parent class name
	 */
	public String getParentClassName() {
		return parentClassName;
	}
}
