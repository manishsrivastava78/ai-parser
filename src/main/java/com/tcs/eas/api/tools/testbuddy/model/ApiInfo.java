package com.tcs.eas.api.tools.testbuddy.model;

import java.io.Serializable;

public class ApiInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1129247659198430183L;

	/**
	 * 
	 */
	private String apiTitle;
	/**
	 * 
	 */
	private String host;
	
	/**
	 * 
	 */
	private String port;

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
	private boolean showRequest;

	/**
	 * 
	 */
	private boolean showResponse;

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return the showRequest
	 */
	public boolean isShowRequest() {
		return showRequest;
	}

	/**
	 * @param showRequest the showRequest to set
	 */
	public void setShowRequest(boolean showRequest) {
		this.showRequest = showRequest;
	}

	/**
	 * @return the showResponse
	 */
	public boolean isShowResponse() {
		return showResponse;
	}

	/**
	 * @param showResponse the showResponse to set
	 */
	public void setShowResponse(boolean showResponse) {
		this.showResponse = showResponse;
	}

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
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
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

}
