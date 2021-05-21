package com.tcs.eas.api.tools.testbuddy.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author 44745
 *
 */
public class Definition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3851491360704070376L;

	/**
	 * 
	 */
	private String defName;

	/**
	 * 
	 */
	private List<Attribute> attributes;

	/**
	 * not required should be deleted
	 */
	private boolean refExist;
	
	/**
	 * 
	 */
	private String refName;

	
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
	 * @return the defName
	 */
	public String getDefName() {
		return defName;
	}

	/**
	 * @param defName the defName to set
	 */
	public void setDefName(String defName) {
		this.defName = defName;
	}

	/**
	 * @return the attributes
	 */
	public List<Attribute> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	/**
	 * @return the refExist
	 */
	public boolean isRefExist() {
		return refExist;
	}

	/**
	 * @param refExist the refExist to set
	 */
	public void setRefExist(boolean refExist) {
		this.refExist = refExist;
	}
}
