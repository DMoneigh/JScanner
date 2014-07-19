package com.jscanner.threat.impl;

import javax.crypto.Cipher;

import com.jscanner.threat.ThreatClass;

/**
 * Represents the Cipher class.
 * 
 * @author Desmond Jackson
 */
public class CipherClass extends ThreatClass {

	/**
	 * Creates an instance of the CipherClass class.
	 */
	public CipherClass() {
		super(Cipher.class);
	}

	@Override
	public void addThreatMethods() {}

	@Override
	public String getReply() {
		return "Is encrypting and decrypting data, could be runnable Java code!";
	}

}
