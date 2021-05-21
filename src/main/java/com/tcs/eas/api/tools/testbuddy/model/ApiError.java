package com.tcs.eas.api.tools.testbuddy.model;

import java.io.Serializable;

/**
 * 
 * @author 44745
 *
 */
public class ApiError implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1724413793454728886L;
	
	private String errorCode;
	
	private String errorDescription;

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * @param errorDescription the errorDescription to set
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
	
	
}
