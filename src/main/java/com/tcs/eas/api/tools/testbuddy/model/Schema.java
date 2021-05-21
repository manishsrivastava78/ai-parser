package com.tcs.eas.api.tools.testbuddy.model;

import java.io.Serializable;

public class Schema implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3047213568371830192L;
	
	/**
	 * 
	 */
	private String type;
	
	/**
	 * 
	 */
	private String format;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	
	
	

}
