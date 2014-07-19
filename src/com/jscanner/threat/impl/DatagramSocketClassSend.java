package com.jscanner.threat.impl;

import java.lang.reflect.Method;
import java.net.DatagramPacket;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the send method in the DatagramSocket class.
 * 
 * @author Desmond Jackson
 */
public class DatagramSocketClassSend extends ThreatMethod {

	/**
	 * Creates an instance of the DatagramSocketClassSend class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public DatagramSocketClassSend(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("send", new Class<?>[] {DatagramPacket.class});
	}

	@Override
	public String getReply() {
		return "One or more packets are written to one or more servers/computers! (udp)";
	}

}
