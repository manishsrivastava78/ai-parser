package com.tcs.eas.api.tools.testbuddy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tcs.eas.api.tools.testbuddy.constant.Constant;

/**
 * 
 * @author 44745
 *
 */
public class Resource implements Serializable,Constant{

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
	 * 
	 */
	private List<HttpHeader> headers ;
	
	/**
	 * 
	 */
	private List<QueryParam> qParams ;
	
	/**
	 * 
	 */
	private List<String> pathParams;
	
	/**
	 * 
	 */
	private String body;
	
	
	
	/**
	 * @return the qParams
	 */
	public List<QueryParam> getqParams() {
		return qParams;
	}

	/**
	 * @param qParams the qParams to set
	 */
	public void setqParams(List<QueryParam> qParams) {
		this.qParams = qParams;
	}

	/**
	 * @return the headers
	 */
	public List<HttpHeader> getHeaders() {
		return headers;
	}

	/**
	 * @param headers the headers to set
	 */
	public void setHeaders(List<HttpHeader> headers) {
		this.headers = headers;
	}

	/**
	 * @return the pathParams
	 */
	public List<String> getPathParams() {
		return pathParams;
	}

	/**
	 * @param pathParams the pathParams to set
	 */
	public void setPathParams(List<String> pathParams) {
		this.pathParams = pathParams;
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
		setRequestData(parameters);
	}
	
	/**
	 * 
	 * @param parameters
	 */
	private void setRequestData(List<ResourceParameter> parameters) {
		List<HttpHeader> headers = new ArrayList<HttpHeader>();
		List<QueryParam> queryParams = new ArrayList<QueryParam>();
		List<String> pathParams = new ArrayList<String>();
		
		HttpHeader header = null;
		QueryParam queryParam = null;
		
		for(ResourceParameter parameter : parameters) {
			switch(parameter.getParameterType()) {
			case HEADER_PARAMETER :
				header = new HttpHeader();
				header.setHeaderName(parameter.getName());
				header.setHeaderValue(parameter.getExample()==null ? UPDATE_ME : parameter.getExample());
				headers.add(header);
				break;
			case QUERY_PARAMETER :
				queryParam = new QueryParam();
				queryParam.setParameterName(parameter.getName());
				queryParam.setParameterValue(parameter.getExample()==null ? UPDATE_ME : parameter.getExample());
				queryParams.add(queryParam);
				break;
			case PATH_PARAMETER:
				pathParams.add(parameter.getName());
				break;
			case BODY_PARAMETER :
				setBody(UPDATE_ME);
				break;
			default:
				break;
			}
		}
		
		setHeaders(headers);
		setqParams(queryParams);
		setPathParams(pathParams);
	}
}
