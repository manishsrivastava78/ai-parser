package com.tcs.eas.api.tools.testbuddy.model;

import java.io.Serializable;

import io.swagger.models.Model;

/**
 * 
 * @author 44745
 *
 */
public class ResourceParameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1512624466122455294L;

	private Boolean allowEmptyValue;
	
	
	/**
	 * Type of parameter. E.g. bodyparameter,queryparameter etc
	 */
	private String parameterType;
	/**
	 * 
	 */
	private String access;

	/**
	 * Parameter name
	 */
	private String name;

	/**
	 * Parameter description
	 */
	private String description;

	/**
	 * Parameter is required true or false
	 */
	private boolean required;

	/**
	 * Type of the parameter e.g. string, integer, double etc
	 */
	private String type;

	/**
	 * Location of the parameter e.g. body, query, path etc
	 */
	private String in;

	/**
	 * Format of the parameter
	 */
	private String format;

	/**
	 * 
	 */
	private String pattern;

	/**
	 * 
	 */
	private Model schema;

	/**
	 * 
	 */
	private String collectionFormat;

	/**
	 * 
	 */
	private String jsonSchema;
	
	
	/**
	 * @return the jsonSchema
	 */
	public String getJsonSchema() {
		return jsonSchema;
	}

	/**
	 * @param jsonSchema the jsonSchema to set
	 */
	public void setJsonSchema(String jsonSchema) {
		this.jsonSchema = jsonSchema;
	}

	/**
	 * @return the allowEmptyValue
	 */
	public Boolean getAllowEmptyValue() {
		return allowEmptyValue;
	}

	/**
	 * @return the collectionFormat
	 */
	public String getCollectionFormat() {
		return collectionFormat;
	}

	/**
	 * @param collectionFormat the collectionFormat to set
	 */
	public void setCollectionFormat(String collectionFormat) {
		this.collectionFormat = collectionFormat;
	}

	/**
	 * @return the allowEmptyValue
	 */
	public Boolean isAllowEmptyValue() {
		return allowEmptyValue;
	}

	/**
	 * @param allowEmptyValue the allowEmptyValue to set
	 */
	public void setAllowEmptyValue(Boolean allowEmptyValue) {
		this.allowEmptyValue = allowEmptyValue;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the in
	 */
	public String getIn() {
		return in;
	}

	/**
	 * @param in the in to set
	 */
	public void setIn(String in) {
		this.in = in;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the parameterType
	 */
	public String getParameterType() {
		return parameterType;
	}

	/**
	 * @param parameterType the parameterType to set
	 */
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	/**
	 * @return the access
	 */
	public String getAccess() {
		return access;
	}

	/**
	 * @param access the access to set
	 */
	public void setAccess(String access) {
		this.access = access;
	}

	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * @return the schema
	 */
	public Model getSchema() {
		return schema;
	}

	/**
	 * @param schema the schema to set
	 */
	public void setSchema(Model schema) {
		this.schema = schema;
	}
}
