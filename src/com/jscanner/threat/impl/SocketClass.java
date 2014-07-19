package com.jscanner.threat.impl;

import java.net.Socket;

import com.jscanner.threat.ThreatClass;

/**
 * Represents the Socket class.
 * 
 * @author Desmond Jackson
 */
public class SocketClass extends ThreatClass {

	/**
	 * Creates an instance of the SocketClass class.
	 */
	public SocketClass() {
		super(Socket.class);
	}

	@Override
	public void addThreatMethods() {
		addThreatMethod(SocketClassGetInputStream.class);
		addThreatMethod(SocketClassGetOutputStream.class);
	}

	@Override
	public String getReply() {
		return "Interacts with one or more servers/computers! (tcp)";
	}

}
