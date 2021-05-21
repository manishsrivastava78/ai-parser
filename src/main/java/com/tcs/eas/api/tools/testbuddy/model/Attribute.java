package com.tcs.eas.api.tools.testbuddy.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author 44745
 *
 */
public class Attribute implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -192223809864927333L;
	
	/**
	 * 
	 */
	private String attributeName;
	
	/**
	 * 
	 */
	private Object attributeValue;
	
	/**
	 * 
	 */
	private String attributeType;
	
	/**
	 * 
	 */
	private Object attributeExample;
	
	/**
	 * 
	 */
	private List<String> enumList;
	
	/**
	 * 
	 */
	private String refName;

	
	
	/**
	 * @return the attributeValue
	 */
	public Object getAttributeValue() {
		return attributeValue;
	}

	/**
	 * @param attributeValue the attributeValue to set
	 */
	public void setAttributeValue(Object attributeValue) {
		this.attributeValue = attributeValue;
	}

	/**
	 * @return the refName
	 */
	public String getRefName() {
		return refName;
	}

	/**
	 * @param refName the refName to set
	 */
	public void setRefName(String refName) {
		this.refName = refName;
	}

	/**
	 * @return the attributeExample
	 */
	public Object getAttributeExample() {
		return attributeExample;
	}

	/**
	 * @param attributeExample the attributeExample to set
	 */
	public void setAttributeExample(Object attributeExample) {
		this.attributeExample = attributeExample;
	}

	/**
	 * @return the enumList
	 */
	public List<String> getEnumList() {
		return enumList;
	}

	/**
	 * @param enumList the enumList to set
	 */
	public void setEnumList(List<String> enumList) {
		this.enumList = enumList;
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * @param attributeName the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * @return the attributeType
	 */
	public String getAttributeType() {
		return attributeType;
	}

	/**
	 * @param attributeType the attributeType to set
	 */
	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}
	
	
}
