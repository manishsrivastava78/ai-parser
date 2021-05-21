package com.tcs.eas.api.tools.testbuddy.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author 44745
 *
 */
public class Resource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4346753487150060977L;
	
	/**
	 * Path of the resource
	 */
	private String path;
	
	/**
	 * Verb name of the resource e.g. GET, POST, UPDATE, DELETE
	 */
	private String method;
	
	/**
	 * 
	 */
	private String operationId;
	
	/**
	 * 
	 */
	private List<ApiResponse> responses;
	
	
	/**
	 * @return the responses
	 */
	public List<ApiResponse> getResponses() {
		return responses;
	}

	/**
	 * @param responses the responses to set
	 */
	public void setResponses(List<ApiResponse> responses) {
		this.responses = responses;
	}

	/**
	 * @return the operationId
	 */
	public String getOperationId() {
		return operationId;
	}

	/**
	 * @param operationId the operationId to set
	 */
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	/**
	 * List of the parameters for the resource
	 */
	private List<ResourceParameter> parameters;

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the parameters
	 */
	public List<ResourceParameter> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(List<ResourceParameter> parameters) {
		this.parameters = parameters;
	}
}
