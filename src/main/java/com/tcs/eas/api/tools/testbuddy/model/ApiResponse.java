package com.tcs.eas.api.tools.testbuddy.model;

import java.io.Serializable;

/**
 * 
 * @author 44745
 *
 */
public class ApiResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5414776749052136290L;
	
	/**
	 * 
	 */
	private String responseKey;
	
	/**
	 * 
	 */
	private String responseDescription;
	
	/**
	 * 
	 */
	private String responseContentType;

	/**
	 * @return the responseKey
	 */
	public String getResponseKey() {
		return responseKey;
	}

	/**
	 * @param responseKey the responseKey to set
	 */
	public void setResponseKey(String responseKey) {
		this.responseKey = responseKey;
	}

	/**
	 * @return the responseDescription
	 */
	public String getResponseDescription() {
		return responseDescription;
	}

	/**
	 * @param responseDescription the responseDescription to set
	 */
	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	/**
	 * @return the responseContentType
	 */
	public String getResponseContentType() {
		return responseContentType;
	}

	/**
	 * @param responseContentType the responseContentType to set
	 */
	public void setResponseContentType(String responseContentType) {
		this.responseContentType = responseContentType;
	}
	
	
}
