package net.jscanner.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;

import net.jscanner.server.handler.impl.RootHandler;
import net.jscanner.server.handler.impl.ScanHandler;
import net.jscanner.server.handler.impl.ScriptHandler;
import net.jscanner.server.handler.impl.SelectHandler;

/**
 * The RESTful web server.
 * 
 * @author Desmond Jackson
 */
public class WebServer {
	
	/**
	 * Starts the web server on the specified port.
	 * 
	 * @param port The port to start the web server on
	 */
	public static void start(int port) {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
			server.createContext("/", new RootHandler());
			server.createContext("/scan", new ScanHandler());
			server.createContext("/script.js", new ScriptHandler());
			server.createContext("/select", new SelectHandler());
			server.setExecutor(Executors.newCachedThreadPool());
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
