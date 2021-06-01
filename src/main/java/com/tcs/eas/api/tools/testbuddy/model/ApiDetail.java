package com.tcs.eas.api.tools.testbuddy.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author 44745
 *
 */
public class ApiDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4676074616361364109L;
	
	/**
	 * 
	 */
	private String apiTitle;
	/**
	 * 
	 */
	private String hostName;
	
	/**
	 * 
	 */
	private String basePath;
	
	/**
	 * 
	 */
	private String httpSchema;
	
	/**
	 * 
	 */
	private List<Resource> resourceList;

	/**
	 * 
	 */
	private List<ApiError> errors;
	
	/**
	 * 
	 */
	private int port;
	
	/**
	 * @return the apiTitle
	 */
	public String getApiTitle() {
		return apiTitle;
	}

	/**
	 * @param apiTitle the apiTitle to set
	 */
	public void setApiTitle(String apiTitle) {
		this.apiTitle = apiTitle;
	}

	/**
	 * @return the errors
	 */
	public List<ApiError> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List<ApiError> errors) {
		this.errors = errors;
	}

	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @param hostName the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * @return the basePath
	 */
	public String getBasePath() {
		return basePath;
	}

	/**
	 * @param basePath the basePath to set
	 */
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

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
	 * @return the resourceList
	 */
	public List<Resource> getResourceList() {
		return resourceList;
	}

	/**
	 * @param resourceList the resourceList to set
	 */
	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
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
