package com.tcs.eas.api.tools.testbuddy.model;

import java.io.Serializable;

public class HttpHeader implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3206634471047720768L;
	
	private String headerName;
	
	private String headerValue;
	
	private boolean required;
	
	

	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @return the headerName
	 */
	public String getHeaderName() {
		return headerName;
	}

	/**
	 * @param headerName the headerName to set
	 */
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	/**
	 * @return the headerValue
	 */
	public String getHeaderValue() {
		return headerValue;
	}

	/**
	 * @param headerValue the headerValue to set
	 */
	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}
	
	

}
