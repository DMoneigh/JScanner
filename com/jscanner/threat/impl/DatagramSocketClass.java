package com.jscanner.threat.impl;

import java.net.DatagramSocket;

import com.jscanner.threat.ThreatClass;

/**
 * Represents the DatagramSocket class.
 * 
 * @author Desmond Jackson
 */
public class DatagramSocketClass extends ThreatClass {

	/**
	 * Creates an instance of the DatagramSocketClass class.
	 */
	public DatagramSocketClass() {
		super(DatagramSocket.class);
	}

	@Override
	public void addThreatMethods() {
		addThreatMethod(DatagramSocketClassConnect.class);
		addThreatMethod(DatagramSocketClassReceive.class);
		addThreatMethod(DatagramSocketClassSend.class);
	}

	@Override
	public String getReply() {
		return "Either interacts with one or more servers/computers, or creates "
				+ "one or more interal servers on your computer! (udp)";
	}

}
