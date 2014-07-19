package com.jscanner.threat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Type;

/**
 * Represents a threatening class.
 * 
 * @author Desmond Jackson
 */
public abstract class ThreatClass extends ThreatObject {

	/**
	 * The threatening class.
	 */
	private Class<?> clss;

	/**
	 * Creates an instance of the ThreatClass class.
	 * 
	 * @param clss The threatening class.
	 */
	public ThreatClass(Class<?> clss) {
		super();
		this.clss = clss;
		addThreatMethods();
	}

	/**
	 * Adds all threatening methods to the threat objects list.
	 */
	public abstract void addThreatMethods();

	/**
	 * Adds a threatening method to the threat object list.
	 * 
	 * @param threatMethod The threatening method.
	 */
	public void addThreatMethod(Class<? extends ThreatMethod> threatMethod) {
		addThreatObject(threatMethod);
	}

	/**
	 * Gets a method based off it's name alone.
	 * 
	 * @param name The name of the threatening method.
	 * 
	 * @return A method based off of its name alone.
	 */
	public Method getMethod(String name) {
		try {
			return clss.getMethod(name, new Class<?>[] {});
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets a method based off of it's name and parameter types.
	 * 
	 * @param name The name of the method.
	 * @param paramTypes The type of the parameters.
	 * 
	 * @return A method based off it's name and parameter types.
	 */
	public Method getMethod(String name, Class<?>[] paramTypes) {
		try {
			return clss.getMethod(name, paramTypes);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets a list of all the instances of threatening methods.
	 * 
	 * @return A list of all the instances of threatening methods. 
	 */
	public List<ThreatMethod> getThreatMethodInstances() {
		List<ThreatMethod> threatMethodInstances = new ArrayList<ThreatMethod>();
		for (Class<? extends ThreatMethod> threatMethod : getThreatMethods()) {
			Constructor<?> c = threatMethod.getConstructors()[0];
				try {
					threatMethodInstances.add((ThreatMethod) c.newInstance(this));
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
		}
		return threatMethodInstances;
	}

	/**
	 * Gets a list of the threatening methods of this class.
	 * 
	 * @return A list of the threatening methods of this class.
	 */
	private List<Class<? extends ThreatMethod>> getThreatMethods() {
		List<Class<? extends ThreatMethod>> threatMethods = new ArrayList<Class<
				? extends ThreatMethod>>();
		for (Class<? extends ThreatObject> threatObject : getThreatObjects())
				threatMethods.add(threatObject.asSubclass(ThreatMethod.class));
		return threatMethods;
	}

	@Override
	public String getName() {
		return Type.getType(clss).toString();
	}

}
