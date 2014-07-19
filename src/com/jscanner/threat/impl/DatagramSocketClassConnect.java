package com.jscanner.threat.impl;

import java.lang.reflect.Method;
import java.net.SocketAddress;

import com.jscanner.threat.ThreatClass;
import com.jscanner.threat.ThreatMethod;

/**
 * Represents the connect method in the DatagramSocket class.
 * 
 * @author Desmond Jackson
 */
public class DatagramSocketClassConnect extends ThreatMethod {

	/**
	 * Creates an instance of the DatagramSocketClassConnect class.
	 * 
	 * @param owner The threatening class that this method is found in.
	 */
	public DatagramSocketClassConnect(ThreatClass owner) {
		super(owner);
	}

	@Override
	public Method getMethod() {
		return owner.getMethod("connect", new Class<?>[] {SocketAddress.class});
	}

	@Override
	public String getReply() {
		return "One or more connections are made with one or more servers/computers! (udp)";
	}

}
