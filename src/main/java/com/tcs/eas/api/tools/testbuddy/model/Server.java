package com.tcs.eas.api.tools.testbuddy.model;

import java.io.Serializable;

public class Server implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6112263573235384145L;

	/**
	 * 
	 */
	private String httpSchema;
	
	/**
	 * 
	 */
	private String host;
	
	/**
	 * 
	 */
	private int port;

	/**
	 * @return the httpSchema
	 */
	public String getHttpSchema() {
		return httpSchema;
	}

	/**
	 * @param httpSchema the httpSchema to set
	 */
	public void setHttpSchema(String httpSchema) {
		this.httpSchema = httpSchema;
	}

	/**
	 * @return the hostName
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param hostName the hostName to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	
}
