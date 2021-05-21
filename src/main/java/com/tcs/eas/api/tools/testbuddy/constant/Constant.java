package com.tcs.eas.api.tools.testbuddy.constant;

import java.io.File;

public interface Constant {
	/**
	 * 
	 */
	public static final String ONE_NEW_LINE = "\n";

	/**
	 * 
	 */
	public static final String TWO_NEW_LINE = "\n\n";

	/**
	 * 
	 */
	public static final String PIPE_CHAR = "|";

	/**
	 * 
	 */
	public static final String SPACE_CHAR = " ";
	
	/**
	 * 
	 */
	public static final String BODY_PARAMETER = "bodyparameter";

	/**
	 * 
	 */
	public static final String COOKIE_PARAMETER = "cookieparameter";

	/**
	 * 
	 */
	public static final String FORM_PARAMETER = "formparameter";

	/**
	 * 
	 */
	public static final String HEADER_PARAMETER = "headerparameter";

	/**
	 * 
	 */
	public static final String PATH_PARAMETER = "pathparameter";

	/**
	 * 
	 */
	public static final String QUERY_PARAMETER = "queryparameter";

	/**
	 * 
	 */
	public static final String REF_PARAMETER = "refparameter";

	/**
	 * 
	 */
	public static final String SERIALIZABLE_PARAMETER = "serializableparameter";

	/**
	 * 
	 */
	public static final String MISSING_FIELD_CODE = "400_01";

	/**
	 * 
	 */
	public static final String MISSING_FIELD_DESC = "%s is not present in the swagger";

	/**
	 * 
	 */
	public static final String MISSING_FILE_EXT_CODE = "400_02";

	/**
	 * 
	 */
	public static final String MISSING_FILE_EXT_DESC = "Missing file extension";

	/**
	 * 
	 */
	public static final String INVALID_FILE_EXT_CODE = "400_03";

	/**
	 * 
	 */
	public static final String INVALID_FILE_EXT_DESC = "Invalid file extension %s";
	
	/**
	 * 
	 */
	public static final String TEST_200_TITLE = "## TEST_200 Perform 200 OK Scenario";

	/**
	 * 
	 */
	public static final String TEST_201_TITLE = "## TEST_201 Perform 201 Created Scenario";

	/**
	 * 
	 */
	public static final String TEST_400_TITLE = "## TEST_400 Perform 400 Bad Request Scenario";

	/**
	 * 
	 */
	public static final String TEST_401_TITLE = "## TEST_401 Perform 401 Unauthorized Scenario";
	
	/**
	 * 
	 */
	public static final String TEST_404_TITLE = "## TEST_404 Perform 404 Resource Not Found Scenario";
	/**
	 * 
	 */
	public static final String DEST_DIRECTORY_PATH =  System.getProperty("user.home")+File.separatorChar+"testbuddy"+File.separatorChar+"gauge-reports"+File.separatorChar+"API-";//"c:\\manish\\gauge-reports\\" + "MyAPI-";
	
	/**
	 * 
	 */
	public static final String SRC_DIRECTORY_PATH =   System.getProperty("user.home")+File.separatorChar+"testbuddy"+File.separatorChar+"project-structure"; //"C:\\manish\\cma-works\\testbuddy\\gauge\\project-structure";
	
	/**
	 * 
	 */
	public static final String WINDOWS_WEB_SERVER_PATH = "C:\\software\\apache-tomcat-10.0.5\\webapps\\gauge-reports\\";
	
	/**
	 * 
	 */
	public static final String LINUX_WEB_SERVER_PATH = "/home/ec2-user/software/apache-tomcat-9.0.45/webapps/gauge-reports/";
	
	/**
	 * 
	 */
	public static final String HTTP_SCHEMA = "httpSchema";
	
	/**
	 * 
	 */
	public static final String API_HOST = "host";
	
	/**
	 * 
	 */
	public static final String API_HOST_PORT = "port";

}
