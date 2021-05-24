package com.tcs.eas.api.tools.testbuddy.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author 44745
 *
 */
public class Spec implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1485529940473991442L;

	/**
	 * 
	 */
	private ApiInfo apiInfo;
	
	/**
	 * 
	 */
	private List<Resource> resources;

	/**
	 * @return the apiInfo
	 */
	public ApiInfo getApiInfo() {
		return apiInfo;
	}

	/**
	 * @param apiInfo the apiInfo to set
	 */
	public void setApiInfo(ApiInfo apiInfo) {
		this.apiInfo = apiInfo;
	}

	/**
	 * @return the resources
	 */
	public List<Resource> getResources() {
		return resources;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	
}
