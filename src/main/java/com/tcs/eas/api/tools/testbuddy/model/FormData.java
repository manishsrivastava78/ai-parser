package com.tcs.eas.api.tools.testbuddy.model;

import java.io.Serializable;
import java.util.List;

public class FormData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 611554329409108127L;
	
	private String httpSchema;
	
	private String host;
	
	private String port; 
	
	private String basePath;
	
	private String resourcePath;
	
	private String method;
	
	private String body;
	
	private List<QueryParam> queryParams;
	
	private List<HttpHeader> httpHeaders;
	
	private String resourceTitle;
	
	private boolean showRequest;
	
	private boolean showResponse;
	
	private String queryParam;
	
	private String httpHeader;

	
	/**
	 * @return the queryParam
	 */
	public String getQueryParam() {
		return queryParam;
	}

	/**
	 * @param queryParam the queryParam to set
	 */
	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}

	/**
	 * @return the httpHeader
	 */
	public String getHttpHeader() {
		return httpHeader;
	}

	/**
	 * @param httpHeader the httpHeader to set
	 */
	public void setHttpHeader(String httpHeader) {
		this.httpHeader = httpHeader;
	}

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
	 * @return the resourceTitle
	 */
	public String getResourceTitle() {
		return resourceTitle;
	}

	/**
	 * @param resourceTitle the resourceTitle to set
	 */
	public void setResourceTitle(String resourceTitle) {
		this.resourceTitle = resourceTitle;
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
	 * @return the resourcePath
	 */
	public String getResourcePath() {
		return resourcePath;
	}

	/**
	 * @param resourcePath the resourcePath to set
	 */
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
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
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the queryParams
	 */
	public List<QueryParam> getQueryParams() {
		return queryParams;
	}

	/**
	 * @param queryParams the queryParams to set
	 */
	public void setQueryParams(List<QueryParam> queryParams) {
		this.queryParams = queryParams;
	}

	/**
	 * @return the httpHeaders
	 */
	public List<HttpHeader> getHttpHeaders() {
		return httpHeaders;
	}

	/**
	 * @param httpHeaders the httpHeaders to set
	 */
	public void setHttpHeaders(List<HttpHeader> httpHeaders) {
		this.httpHeaders = httpHeaders;
	}
	
	

}
