package net.jscanner.util;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.security.Permission;

import net.jscanner.JScanner;

/**
 * A security policy for the execution of archives.
 * 
 * @author Desmond Jackson
 */
public class ExecutionSecurityManager extends SecurityManager {

	/**
	 * The JScanner instance that invoked this security policy.
	 */
	private JScanner jScanner;

	/**
	 * Creates a new execution security manager policy.
	 * 
	 * @param jScanner The JScanner instance that invoked this security policy
	 */
	public ExecutionSecurityManager(JScanner jScanner) {
		super();
		this.jScanner = jScanner;
	}

	@Override
	public void checkAccept(String host, int port) {
		jScanner.print("Received a connection from " + host + ":" + port);
	}

	@Override
	public void checkAccess(Thread thread) {}

	@Override
	public void checkAccess(ThreadGroup threatGroup) {}

	@Override
	public void checkAwtEventQueueAccess() {}

	@Override
	public void checkConnect(String host, int port) {
		jScanner.print("Connecting to " + host + ":" + port);
	}

	@Override
	public void checkConnect(String host, int port, Object context) {
		jScanner.print("Connecting to " + host + ":" + port);
	}

	@Override
	public void checkCreateClassLoader() {}

	@Override
	public void checkDelete(String file) {
		jScanner.print("Deleting: " + file);
	}

	@Override
	public void checkExec(String command) {
		jScanner.print("Executed command via cmd/terminal: " + command);
	}

	@Override
	public void checkExit(int status) {
		jScanner.print("Exiting with: " + status);
	}

	@Override
	public void checkLink(String library) {
		jScanner.print("Using library: " + library);
	}

	@Override
	public void checkListen(int port) {
		jScanner.print("Listening for connections on localhost:" + port);
	}

	@Override
	public void checkMemberAccess(Class<?> clazz, int which) {}

	@Override
	public void checkMulticast(InetAddress inetAddress) {}

	@Override
	public void checkMulticast(InetAddress inetAddress, byte ttl) {}

	@Override
	public void checkPackageAccess(String packkage) {}

	@Override
	public void checkPackageDefinition(String packkage) {}

	@Override
	public void checkPermission(Permission permission) {}

	@Override
	public void checkPermission(Permission permission, Object context) {}

	@Override
	public void checkPrintJobAccess() {
		jScanner.print("Program is making print job requests.");
	}

	@Override
	public void checkPropertiesAccess() {
		jScanner.print("Program is modifying/reading properties.");
	}

	@Override
	public void checkPropertyAccess(String key) {
		jScanner.print("Program is modifying/reading property: " + key);
	}

	@Override
	public void checkRead(FileDescriptor fileDescriptor) {}

	@Override
	public void checkRead(String file) {
		jScanner.print("Reading data from: " + file);
	}

	@Override
	public void checkRead(String file, Object context) {
		jScanner.print("Reading data from: " + file);
	}

	@Override
	public void checkSecurityAccess(String target) {
		jScanner.print("Checking security access from: " + target);
	}

	@Override
	public void checkSetFactory() {}

	@Override
	public void checkSystemClipboardAccess() {
		jScanner.print("Program is interacting with your clipboard.");
	}

	@Override
	public boolean checkTopLevelWindow(Object window) {
		return true;
	}

	@Override
	public void checkWrite(FileDescriptor fileDescriptor) {}

	@Override
	public void checkWrite(String file) {
		jScanner.print("Writing data to: " + file);
	}

}
