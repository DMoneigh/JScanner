package com.jscanner.threat.impl;

import java.lang.reflect.Method;
import java.net.DatagramPacket;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the receive method in the DatagramSocket class.
 * 
 * @author Desmond Jackson
 */
public class DatagramSocketClassReceive extends ThreatMethod {

	/**
	 * Creates an instance of the DatagramSocketClassReceive class. 
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public DatagramSocketClassReceive(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("receive", new Class<?>[] {DatagramPacket.class});
	}

	@Override
	public String getReply() {
		return "Receives one or more packets from one or more servers/computers! (udp)";
	}

}
