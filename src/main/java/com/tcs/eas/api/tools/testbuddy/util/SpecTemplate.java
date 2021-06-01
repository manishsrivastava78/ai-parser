package com.tcs.eas.api.tools.testbuddy.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.tcs.eas.api.tools.testbuddy.constant.Constant;
import com.tcs.eas.api.tools.testbuddy.model.ApiInfo;
import com.tcs.eas.api.tools.testbuddy.model.ApiResponse;
import com.tcs.eas.api.tools.testbuddy.model.FormData;
import com.tcs.eas.api.tools.testbuddy.model.HttpHeader;
import com.tcs.eas.api.tools.testbuddy.model.QueryParam;
import com.tcs.eas.api.tools.testbuddy.model.Resource;
import com.tcs.eas.api.tools.testbuddy.model.Spec;

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
	 */
	private Spec spec;

	/**
	 * 
	 * @param formData
	 */
	public SpecTemplate(FormData formData) {
		this.formData = formData;
	}

	/**
	 * 
	 * @param spec
	 */
	public SpecTemplate(Spec spec) {
		this.spec = spec;
	}

	/**
	 * 
	 * @param filePath
	 */
	public void writeCliSpecs(String filePath) {
		ApiInfo apiInfo = spec.getApiInfo();
		List<Resource> resources = spec.getResources();
		String specFile = null;
		FileWriter myWriter1 = null;
		try {
			myWriter1 = new FileWriter(filePath + File.separatorChar + "specs" + File.separatorChar + "common.cpt");
			myWriter1.write(getRequestSetup(apiInfo.isShowRequest(), apiInfo.isShowResponse()));
			myWriter1.close();
		} catch (Exception e) {
			try {
				if (myWriter1 != null)
					myWriter1.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		for (Resource resource : resources) {
			String operationId = resource.getOperationId();
			if (operationId != null && operationId.trim().length() > 0) {
				specFile = operationId.replaceAll("\\s+", "");
			} else {
				specFile = Helper.getRandomNumberString();
			}

			try {
				FileWriter myWriter = new FileWriter(
						filePath + File.separatorChar + "specs" + File.separatorChar + specFile + ".spec");
				myWriter.write(setCliSpecificationHeading(resource));
				myWriter.write("* request setup\n");
				myWriter.write(generateSpecTemplate(resource, filePath));
				myWriter.close();
				logger.info("Successfully wrote specification.");
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
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
	private String setCliSpecificationHeading(Resource resource) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(SPEC_TITLE);
		stringBuilder.append(TEST_STEP_COMMENT);
		stringBuilder
				.append("# " + ((resource.getOperationId() != null && resource.getOperationId().trim().length() > 0)
						? resource.getOperationId()
						: " TestBuddy Test Execution" + Helper.getRandomNumberString()));
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
		stringBuilder
				.append("* use path \"" + formData.getBasePath() + formData.getResourcePath() + "/testbuddy9876123");
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
			} catch (Exception e) {
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
		stringBuilder
				.append("* use path \"" + formData.getBasePath() + formData.getResourcePath() + "/testbuddy9876123\"");
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

	/**
	 * 
	 * @param resource
	 * @return
	 */
	private String generateSpecTemplate(Resource resource, String filePath) {
		List<ApiResponse> apiResponses = resource.getResponses();
		StringBuilder stringBuilder = new StringBuilder();
		for (ApiResponse apiResponse : apiResponses) {
			switch (apiResponse.getResponseKey()) {
			case RESPONSE_200:
				stringBuilder.append(generateCliGetSpecTemplate_200(resource, apiResponse));
				break;
			case RESPONSE_201:
				stringBuilder.append(generateCliGetSpecTemplate_201(resource, filePath, apiResponse));
				break;
			case RESPONSE_204:
				stringBuilder.append(generateCliGetSpecTemplate_204(resource, apiResponse));
				break;
			case RESPONSE_400:
				stringBuilder.append(generateCliGetSpecTemplate_400(resource, apiResponse));
				break;
			case RESPONSE_401:
				stringBuilder.append(generateCliGetSpecTemplate_401(resource, apiResponse));
				break;
			case RESPONSE_404:
				stringBuilder.append(generateCliGetSpecTemplate_404(resource, apiResponse));
				break;
			case RESPONSE_405:
				stringBuilder.append(generateCliGetSpecTemplate_405(resource, apiResponse));
				break;
			default:
				break;
			}

		}

		return stringBuilder.toString();
	}

	/**
	 * 
	 * @param resource
	 * @param apiResponse
	 * @return
	 */
	private String generateCliGetSpecTemplate_200(Resource resource, ApiResponse apiResponse) {
		StringBuilder stringBuilder = new StringBuilder();
		if (spec.getApiInfo().getBasePath() != null) {
			stringBuilder.append(getCommonSteps(TEST_200_TITLE, spec.getApiInfo().getBasePath() + resource.getPath(),
					resource.getHeaders(), resource.getqParams(), resource.getMethod(), RESPONSE_200));
		} else {
			stringBuilder.append(getCommonSteps(TEST_200_TITLE, resource.getPath(), resource.getHeaders(),
					resource.getqParams(), resource.getMethod(), RESPONSE_200));
		}
		stringBuilder.append("* The response content-type should be \"" + apiResponse.getResponseContentType() + "\"");
		stringBuilder.append(ONE_NEW_LINE);
		return stringBuilder.toString();
	}

	/**
	 * 
	 * @param resource
	 * @param apiResponse
	 * @return
	 */
	private String generateCliGetSpecTemplate_204(Resource resource, ApiResponse apiResponse) {
		if (spec.getApiInfo().getBasePath() != null) {
			return getCommonSteps(TEST_204_TITLE, spec.getApiInfo().getBasePath() + resource.getPath(),
					resource.getHeaders(), resource.getqParams(), resource.getMethod(), RESPONSE_204);
		} else {
			return getCommonSteps(TEST_204_TITLE, resource.getPath(), resource.getHeaders(), resource.getqParams(),
					resource.getMethod(), RESPONSE_204);
		}
	}

	/**
	 * 
	 * @param resource
	 * @param apiResponse
	 * @return
	 */
	private String generateCliGetSpecTemplate_401(Resource resource, ApiResponse apiResponse) {
		if (spec.getApiInfo().getBasePath() != null) {
			return getCommonSteps(TEST_401_TITLE, spec.getApiInfo().getBasePath() + resource.getPath(), null,
					resource.getqParams(), resource.getMethod(), RESPONSE_401);
		} else {
			return getCommonSteps(TEST_401_TITLE, resource.getPath(), null, resource.getqParams(), resource.getMethod(),
					RESPONSE_401);
		}
	}

	/**
	 * 
	 * @param resource
	 * @param apiResponse
	 * @return
	 */
	private String generateCliGetSpecTemplate_404(Resource resource, ApiResponse apiResponse) {
		if (spec.getApiInfo().getBasePath() != null) {
			return getCommonSteps(TEST_404_TITLE,
					spec.getApiInfo().getBasePath() + resource.getPath() + "/testbuddy9876123", resource.getHeaders(),
					resource.getqParams(), resource.getMethod(), RESPONSE_404);
		} else {
			return getCommonSteps(TEST_404_TITLE, resource.getPath() + "/testbuddy9876123", resource.getHeaders(),
					resource.getqParams(), resource.getMethod(), RESPONSE_404);
		}
	}

	/**
	 * 
	 * @param resource
	 * @param apiResponse
	 * @return
	 */
	private String generateCliGetSpecTemplate_405(Resource resource, ApiResponse apiResponse) {
		if (spec.getApiInfo().getBasePath() != null) {
			return getCommonSteps(TEST_405_TITLE, spec.getApiInfo().getBasePath() + resource.getPath(),
					resource.getHeaders(), resource.getqParams(), resource.getMethod(), RESPONSE_405);
		} else {
			return getCommonSteps(TEST_405_TITLE, resource.getPath(), resource.getHeaders(), resource.getqParams(),
					resource.getMethod(), RESPONSE_405);
		}
	}

	/**
	 * 
	 * @return
	 */
	private String generateCliGetSpecTemplate_201(Resource resource, String filePath, ApiResponse apiResponse) {
		StringBuilder stringBuilder = new StringBuilder();
		// stringBuilder.append(TWO_NEW_LINE);
		// stringBuilder.append(TEST_STEP_COMMENT);
		stringBuilder.append(TWO_NEW_LINE);
		stringBuilder.append(TEST_201_TITLE);
		stringBuilder.append(ONE_NEW_LINE);
		if (spec.getApiInfo().getBasePath() != null) {
			stringBuilder.append("* use path \"" + spec.getApiInfo().getBasePath() + resource.getPath() + "\"");
		} else {
			stringBuilder.append("* use path \"" + resource.getPath() + "\"");
		}

		stringBuilder.append(ONE_NEW_LINE);
		if (resource.getHeaders() != null && resource.getHeaders().size() > 0) {
			stringBuilder.append(getHeaders(resource.getHeaders()));
		}
		if (resource.getqParams() != null && resource.getqParams().size() > 0) {
			stringBuilder.append(getQueryParams(resource.getqParams()));
		}
		if (resource.getBody() != null && !resource.getBody().isEmpty()) {
			try {
				FileWriter myWriter = new FileWriter(
						filePath + File.separatorChar + "data" + File.separatorChar + "data.txt");
				myWriter.write(resource.getBody());
				myWriter.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			// stringBuilder.append("* use body from file \"data/data.txt\"");
			// stringBuilder.append(ONE_NEW_LINE);
		} else {
			try {
				FileWriter myWriter = new FileWriter(
						filePath + File.separatorChar + "data" + File.separatorChar + "data.txt");
				myWriter.write("update me");
				myWriter.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			// stringBuilder.append("* use body from file \"data/data.txt\"");
			// stringBuilder.append(ONE_NEW_LINE);
		}
		stringBuilder.append("* use body from file \"data/data.txt\"");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* send \"" + resource.getMethod().toUpperCase() + "\" http request");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* Then response status code is \"201\"");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* The response content-type should be \"" + apiResponse.getResponseContentType() + "\"");
		stringBuilder.append(ONE_NEW_LINE);
		return stringBuilder.toString();
	}

	/**
	 * 
	 * @return
	 */
	private String generateCliGetSpecTemplate_400(Resource resource, ApiResponse apiResponse) {
		StringBuilder stringBuilder = new StringBuilder();
		// stringBuilder.append(TWO_NEW_LINE);
		// stringBuilder.append(TEST_STEP_COMMENT);
		stringBuilder.append(TWO_NEW_LINE);
		stringBuilder.append(TEST_400_TITLE);
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* use path \"" + spec.getApiInfo().getBasePath() + resource.getPath() + "\"");
		stringBuilder.append(ONE_NEW_LINE);
		if (resource.getHeaders() != null && resource.getHeaders().size() > 0) {
			stringBuilder.append(getHeaders(resource.getHeaders()));
		}
		if ((resource.getqParams() != null && resource.getqParams().size() > 0) || true) {
			stringBuilder.append(getQueryParams(resource.getqParams()));
			stringBuilder.append(PIPE_CHAR + SPACE_CHAR + "tool_name_2021" + SPACE_CHAR + PIPE_CHAR + SPACE_CHAR
					+ "testbuddy" + SPACE_CHAR + PIPE_CHAR);
			stringBuilder.append(ONE_NEW_LINE);
		}
		stringBuilder.append("* send \"" + resource.getMethod().toUpperCase() + "\" http request");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* Then response status code is \"400\"");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* The response content-type should be \"" + apiResponse.getResponseContentType() + "\"");
		stringBuilder.append(ONE_NEW_LINE);
		return stringBuilder.toString();
	}

	/**
	 * 
	 * @param title
	 * @param resourcePath
	 * @param headers
	 * @param queryParams
	 * @param method
	 * @param responseCode
	 * @param responseContentType
	 * @return
	 */
	private String getCommonSteps(String title, String resourcePath, List<HttpHeader> headers,
			List<QueryParam> queryParams, String method, String responseCode) {
		StringBuilder stringBuilder = new StringBuilder();
		// stringBuilder.append(TWO_NEW_LINE);
		// stringBuilder.append(TEST_STEP_COMMENT);
		stringBuilder.append(TWO_NEW_LINE);
		stringBuilder.append(title);
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* use path \"" + resourcePath + "\"");
		stringBuilder.append(ONE_NEW_LINE);
		if (headers != null && headers.size() > 0) {
			stringBuilder.append(getHeaders(headers));
		}
		if (queryParams != null && queryParams.size() > 0) {
			stringBuilder.append(getQueryParams(queryParams));
		}
		stringBuilder.append("* send \"" + method.toUpperCase() + "\" http request");
		stringBuilder.append(ONE_NEW_LINE);
		stringBuilder.append("* Then response status code is \"" + responseCode + "\"");
		stringBuilder.append(ONE_NEW_LINE);
		return stringBuilder.toString();
	}
}
