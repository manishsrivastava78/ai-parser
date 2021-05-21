package com.tcs.eas.api.tools.testbuddy.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.tcs.eas.api.tools.testbuddy.constant.Constant;
import com.tcs.eas.api.tools.testbuddy.model.FormData;
import com.tcs.eas.api.tools.testbuddy.model.HttpHeader;
import com.tcs.eas.api.tools.testbuddy.model.QueryParam;

/**
 * 
 * @author 44745
 *
 */
public class SpecTemplate implements Constant {

	// Logger for logging
	final static Logger logger = Logger.getLogger(SpecTemplate.class);
	
	/**
	 * 
	 */
	private FormData formData;

	/**
	 * 
	 * @param formData
	 */
	public SpecTemplate(FormData formData) {
		this.formData = formData;
	}

	/**
	 * 
	 * @param filePath
	 */
	public void writeSpecs(String filePath) {
		logger.info("Writing specification...");
		try {
			FileWriter myWriter = new FileWriter(
					filePath + File.separatorChar + "specs" + File.separatorChar + "testbuddy.spec");
			FileWriter myWriter1 = new FileWriter(
					filePath + File.separatorChar + "specs" + File.separatorChar + "common.cpt");
			myWriter1.write(getRequestSetup(formData.isShowRequest(), formData.isShowResponse()));
			switch (formData.getMethod().toUpperCase()) {
			case "GET":
				myWriter.write(setSpecificationHeading());
				myWriter.write("* request setup\n");
				myWriter.write(generateGetSpecTemplate_200());
				myWriter.write(generateGetSpecTemplate_400());
				myWriter.write(generateGetSpecTemplate_401());
				myWriter.write(generateGetSpecTemplate_404());
				break;
			case "POST":
				myWriter.write(setSpecificationHeading());
				myWriter.write("* request setup\n");
				myWriter.write(generatePostSpecTemplate_201(filePath));
				myWriter.write(generateGetSpecTemplate_400());
				myWriter.write(generateGetSpecTemplate_401());
				myWriter.write(generatePostSpecTemplate_404());
				break;
			}
			myWriter.close();
			myWriter1.close();
			logger.info("Successfully wrote specification.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	private String setSpecificationHeading() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("# " + ((formData.getResourceTitle() != null && formData.getResourceTitle().length() > 0)
				? formData.getResourceTitle()
				: " TestBuddy Test Execution."));
		stringBuilder.append(TWO_NEW_LINE);
		return stringBuilder.toString();
	}

	/**
	 * 
	 * @return
	 */
	private String generateGetSpecTemplate_200() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(TWO_NEW_LINE);
		stringBuilder.append(TEST_200_TITLE);
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* use path \"" + formData.getBasePath() + formData.getResourcePath() + "\"");
		stringBuilder.append(ONE_NEW_LINE);
		if (formData.getHttpHeaders() != null && formData.getHttpHeaders().size() > 0) {
			stringBuilder.append(getHeaders(formData.getHttpHeaders()));
		}
		if (formData.getQueryParams() != null && formData.getQueryParams().size() > 0) {
			stringBuilder.append(getQueryParams(formData.getQueryParams()));
		}
		stringBuilder.append("* send \"" + formData.getMethod().toUpperCase() + "\" http request");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* Then response status code is \"200\"");
		stringBuilder.append(ONE_NEW_LINE);
		return stringBuilder.toString();
	}

	/**
	 * Need to discuss
	 * 
	 * @return
	 */
	private String generateGetSpecTemplate_204() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(TWO_NEW_LINE);
		stringBuilder.append("## Test__204");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* use path \"" + formData.getBasePath() + formData.getResourcePath() + "\"");
		stringBuilder.append(ONE_NEW_LINE);
		if (formData.getHttpHeaders() != null && formData.getHttpHeaders().size() > 0) {
			stringBuilder.append(getHeaders(formData.getHttpHeaders()));
		}
		if ((formData.getQueryParams() != null && formData.getQueryParams().size() > 0) || true) {
			stringBuilder.append(getQueryParams(formData.getQueryParams()));
			stringBuilder.append(PIPE_CHAR + SPACE_CHAR + "tool_name_2021" + SPACE_CHAR + PIPE_CHAR + SPACE_CHAR
					+ "testbuddy" + SPACE_CHAR + PIPE_CHAR);
			stringBuilder.append(ONE_NEW_LINE);
		}
		stringBuilder.append("* send \"" + formData.getMethod().toUpperCase() + "\" http request");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* Then response status code is \"204\"");
		stringBuilder.append(ONE_NEW_LINE);
		return stringBuilder.toString();
	}

	/**
	 * 
	 * @return
	 */
	private String generateGetSpecTemplate_400() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(TWO_NEW_LINE);
		stringBuilder.append(TEST_400_TITLE);
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* use path \"" + formData.getBasePath() + formData.getResourcePath() + "\"");
		stringBuilder.append(ONE_NEW_LINE);
		if (formData.getHttpHeaders() != null && formData.getHttpHeaders().size() > 0) {
			stringBuilder.append(getHeaders(formData.getHttpHeaders()));
		}
		if ((formData.getQueryParams() != null && formData.getQueryParams().size() > 0) || true) {
			stringBuilder.append(getQueryParams(formData.getQueryParams()));
			stringBuilder.append(PIPE_CHAR + SPACE_CHAR + "tool_name_2021" + SPACE_CHAR + PIPE_CHAR + SPACE_CHAR
					+ "testbuddy" + SPACE_CHAR + PIPE_CHAR);
			stringBuilder.append(ONE_NEW_LINE);
		}
		stringBuilder.append("* send \"" + formData.getMethod().toUpperCase() + "\" http request");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* Then response status code is \"400\"");
		stringBuilder.append(ONE_NEW_LINE);
		return stringBuilder.toString();
	}

	/**
	 * 
	 * @return
	 */
	private String generateGetSpecTemplate_401() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(TWO_NEW_LINE);
		stringBuilder.append(TEST_401_TITLE);
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* use path \"" + formData.getBasePath() + formData.getResourcePath() + "\"");
		stringBuilder.append(ONE_NEW_LINE);
		if ((formData.getQueryParams() != null && formData.getQueryParams().size() > 0)) {
			stringBuilder.append(getQueryParams(formData.getQueryParams()));
		}
		stringBuilder.append("* send \"" + formData.getMethod().toUpperCase() + "\" http request");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* Then response status code is \"401\"");
		stringBuilder.append(ONE_NEW_LINE);
		return stringBuilder.toString();
	}


	/**
	 * 
	 * @return
	 */
	private String generateGetSpecTemplate_404() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(TWO_NEW_LINE);
		stringBuilder.append(TEST_404_TITLE);
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* use path \"" + formData.getBasePath() + formData.getResourcePath() + "/testbuddy9876123\"");
		stringBuilder.append(ONE_NEW_LINE);
		if (formData.getHttpHeaders() != null && formData.getHttpHeaders().size() > 0) {
			stringBuilder.append(getHeaders(formData.getHttpHeaders()));
		}
		if (formData.getQueryParams() != null && formData.getQueryParams().size() > 0) {
			stringBuilder.append(getQueryParams(formData.getQueryParams()));
		}
		stringBuilder.append("* send \"" + formData.getMethod().toUpperCase() + "\" http request");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* Then response status code is \"404\"");
		stringBuilder.append(ONE_NEW_LINE);
		return stringBuilder.toString();
	
	}

	
	/**
	 * 
	 * @return
	 */
	private String generatePostSpecTemplate_201(String filePath) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(TWO_NEW_LINE);
		stringBuilder.append(TEST_201_TITLE);
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* use path \"" + formData.getBasePath() + formData.getResourcePath() + "\"");
		stringBuilder.append(ONE_NEW_LINE);
		if (formData.getHttpHeaders() != null && formData.getHttpHeaders().size() > 0) {
			stringBuilder.append(getHeaders(formData.getHttpHeaders()));
		}
		if (formData.getQueryParams() != null && formData.getQueryParams().size() > 0) {
			stringBuilder.append(getQueryParams(formData.getQueryParams()));
		}
		if (formData.getBody() != null && !formData.getBody().isEmpty()) {
			try {
				FileWriter myWriter = new FileWriter(
						filePath + File.separatorChar + "data" + File.separatorChar + "data.txt");
				myWriter.write(formData.getBody());
				myWriter.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
			stringBuilder.append("* use body from file \"data/data.txt\"");
			stringBuilder.append(ONE_NEW_LINE);
		}
		stringBuilder.append("* send \"" + formData.getMethod().toUpperCase() + "\" http request");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* Then response status code is \"201\"");
		stringBuilder.append(ONE_NEW_LINE);
		return stringBuilder.toString();
	}

	/**
	 * 
	 * @return
	 */
	private String generatePostSpecTemplate_404() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(TWO_NEW_LINE);
		stringBuilder.append(TEST_404_TITLE);
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* use path \"" + formData.getBasePath() + formData.getResourcePath() + "/testbuddy9876123\"");
		stringBuilder.append(ONE_NEW_LINE);
		if (formData.getHttpHeaders() != null && formData.getHttpHeaders().size() > 0) {
			stringBuilder.append(getHeaders(formData.getHttpHeaders()));
		}
		if (formData.getQueryParams() != null && formData.getQueryParams().size() > 0) {
			stringBuilder.append(getQueryParams(formData.getQueryParams()));
		}
		if (formData.getBody() != null && !formData.getBody().isEmpty()) {
			stringBuilder.append("* use body from file \"data/data.txt\"");
			stringBuilder.append(ONE_NEW_LINE);
		}
		stringBuilder.append("* send \"" + formData.getMethod().toUpperCase() + "\" http request");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* Then response status code is \"404\"");
		stringBuilder.append(ONE_NEW_LINE);
		return stringBuilder.toString();
	}

	/**
	 * 
	 * @param showRequest
	 * @param showResponse
	 * @return
	 */
	private String getRequestSetup(boolean showRequest, boolean showResponse) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("# request setup");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* reset rest-assured");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* reset scenario store");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* " + getReqResVisibility(showRequest) + " request");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* " + getReqResVisibility(showResponse) + " response");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* set base URI");
		stringBuilder.append(ONE_NEW_LINE);
		return stringBuilder.toString();
	}

	/**
	 * 
	 * @param flag
	 * @return
	 */
	private String getReqResVisibility(boolean flag) {
		return flag ? "show" : "hide";
	}

	/**
	 * 
	 * @param queryParams
	 * @return
	 */
	private String getQueryParams(List<QueryParam> queryParams) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("* set query parameters from the table");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("| key | value |");
		stringBuilder.append(ONE_NEW_LINE);
		if (queryParams != null) {
			for (QueryParam queryParam : queryParams) {
				stringBuilder.append(PIPE_CHAR + SPACE_CHAR + queryParam.getParameterName() + SPACE_CHAR + PIPE_CHAR
						+ SPACE_CHAR + queryParam.getParameterValue() + SPACE_CHAR + PIPE_CHAR);
				stringBuilder.append(ONE_NEW_LINE);
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * 
	 * @param httpHeaders
	 * @return
	 */
	private String getHeaders(List<HttpHeader> httpHeaders) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("* set headers");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("| header | value |");
		stringBuilder.append(ONE_NEW_LINE);
		for (HttpHeader httpHeader : httpHeaders) {
			stringBuilder.append(PIPE_CHAR + SPACE_CHAR + httpHeader.getHeaderName() + SPACE_CHAR + PIPE_CHAR
					+ SPACE_CHAR + httpHeader.getHeaderValue() + SPACE_CHAR + PIPE_CHAR);
			stringBuilder.append(ONE_NEW_LINE);
		}
		return stringBuilder.toString();
	}

}
