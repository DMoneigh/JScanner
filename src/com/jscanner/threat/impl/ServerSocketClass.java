package com.jscanner.threat.impl;

import java.net.ServerSocket;

import com.jscanner.threat.ThreatClass;

/**
 * Represents the ServerSocket class.
 * 
 * @author Desmond Jackson
 */
public class ServerSocketClass extends ThreatClass {

	/**
	 * Creates an instance of the ServerSocketClass class.
	 */
	public ServerSocketClass() {
		super(ServerSocket.class);
	}

	@Override
	public void addThreatMethods() {
		addThreatMethod(ServerSocketClassAccept.class);
	}

	@Override
	public String getReply() {
		return "Creates one or more internal servers on your computer! (tcp)";
	}

}
