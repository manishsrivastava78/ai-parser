package com.tcs.eas.api.tools.testbuddy.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tcs.eas.api.tools.testbuddy.constant.Constant;
import com.tcs.eas.api.tools.testbuddy.model.ApiDetail;
import com.tcs.eas.api.tools.testbuddy.model.ApiError;
import com.tcs.eas.api.tools.testbuddy.model.ApiResponse;
import com.tcs.eas.api.tools.testbuddy.model.Definition;
import com.tcs.eas.api.tools.testbuddy.model.Resource;
import com.tcs.eas.api.tools.testbuddy.model.ResourceParameter;
import com.tcs.eas.api.tools.testbuddy.model.Server;
import com.tcs.eas.api.tools.testbuddy.util.Helper;

import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.Parameter;
import io.swagger.parser.SwaggerParser;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.parser.OpenAPIV3Parser;

/**
 * 
 * @author 44745
 *
 */
public class ApiParser implements Constant {

	// Logger for logging
	final static Logger logger = Logger.getLogger(ApiParser.class);

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Swagger parsing...");
		String swaggerPath = "C:\\manish\\cma-works\\testbuddy\\openapi3-body.json";// "C:\\manish\\cma-works\\testbuddy\\pet2.json";

		//String swaggerPath1 = "C:\\manish\\cma-works\\testbuddy\\swagger-def-rec.yaml";

		//String yamlPath = "C:\\manish\\cma-works\\testbuddy\\swagger-test.yaml";
		ApiParser main = new ApiParser();
		ApiDetail apiDetail = main.getApiDetail(swaggerPath, "3");
		System.out.println(apiDetail);
		// main.printResults(apiDetail);
		// System.out.println(main.getFileExtension("C:\\manish\\cma-works\\testbuddy\\pet2").length());
	}

	/**
	 * 
	 * @param swaggerPath
	 * @return
	 */
	private ApiDetail getApiDetailOas2(String swaggerPath) {

		// Parsed information of the swagger
		ApiDetail apiDetail = new ApiDetail();

		// Get file extension
		String fileExt = Helper.getFileExtension(swaggerPath);

		List<ApiError> apiErrorList = null;

		Swagger swagger = null;

		if (fileExt.length() == 0) {
			apiErrorList = new ArrayList<ApiError>();
			ApiError apiError = new ApiError();
			apiError.setErrorCode(MISSING_FILE_EXT_CODE);
			apiError.setErrorDescription(MISSING_FILE_EXT_DESC);
			apiErrorList.add(apiError);
			apiDetail.setErrors(apiErrorList);
			logger.error(MISSING_FILE_EXT_CODE + " " + MISSING_FILE_EXT_DESC);
			return apiDetail;
		} else {
			if (fileExt.equalsIgnoreCase("json")) {
				swagger = new SwaggerParser().read(swaggerPath);
			} else if (fileExt.equalsIgnoreCase("yaml") || fileExt.equalsIgnoreCase("yml")) {
				String jsonString = Helper.convertYamlToJson(swaggerPath);
				logger.info("JSON value of yaml definition \n" + jsonString);
				swagger = new SwaggerParser().parse(jsonString);
			} else {
				apiErrorList = new ArrayList<ApiError>();
				ApiError apiError = new ApiError();
				apiError.setErrorCode(INVALID_FILE_EXT_CODE);
				apiError.setErrorDescription(String.format(INVALID_FILE_EXT_DESC, fileExt));
				apiErrorList.add(apiError);
				apiDetail.setErrors(apiErrorList);
				logger.error(INVALID_FILE_EXT_CODE + " " + String.format(INVALID_FILE_EXT_DESC, fileExt));
				return apiDetail;
			}
		}

		List<ApiError> apiErrors = getErrorsOas2(swagger);
		if (apiErrors.size() > 0) {
			logger.info("Error exists in the swagger file.");
			for (ApiError apiError : apiErrors) {
				logger.info(apiError.getErrorDescription());
			}
			apiDetail.setErrors(apiErrors);
			return apiDetail;
		} else {
			// List of resources of the API
			List<Resource> resourceList = new ArrayList<Resource>();

			// List of the path
			List<String> pathList = new ArrayList<String>();
			// Get the model definitions defined in the swagger
			Map<String, Definition> definitionMap = Helper.getDefinitions(swagger);

			// Get path information
			Map<String, Path> paths = swagger.getPaths();

			// Fetching paths value
			for (Map.Entry<String, Path> set : paths.entrySet()) {
				pathList.add(set.getKey());
			}

			Resource resource = null;

			List<ApiResponse> apiResponses;

			List<ResourceParameter> resourceParamList = null;
			for (String key : pathList) {
				Path path1 = paths.get(key);
				Map<HttpMethod, Operation> opeMap = path1.getOperationMap();
				for (Map.Entry<HttpMethod, Operation> set : opeMap.entrySet()) {
					apiResponses = new ArrayList<>();
					resource = new Resource();
					resource.setPath(key);
					String method = set.getKey().name();
					Operation ope = opeMap.get(set.getKey());
					/*
					 * List<Map<String, List<String>>> security = ope.getSecurity(); for(int
					 * i=0;i<security.size();i++) { Map<String, List<String>> sec = security.get(i);
					 * for (Map.Entry<String, List<String>> response : sec.entrySet()) { String
					 * key1123 = response.getKey(); List<String> value = response.getValue();
					 * System.out.println(value.size()); } }
					 */
					Map<String, Response> responses = ope.getResponses();

					for (Map.Entry<String, Response> response : responses.entrySet()) {
						ApiResponse apiResponse = new ApiResponse();
						apiResponse.setResponseKey(response.getKey());
						String resDesc = response.getValue().getDescription();
						apiResponse.setResponseDescription(resDesc != null ? resDesc : "");
						apiResponse.setResponseContentType(ope.getProduces().get(0));
						apiResponses.add(apiResponse);
					}

					List<Parameter> paramList = ope.getParameters();
					resourceParamList = new ArrayList<ResourceParameter>();
					if (paramList != null) {
						for (Parameter p : paramList) {
							ResourceParameter resParameter = Helper.getResourceParameter(p);
							resParameter.setJsonSchema(Helper.getResourceBodySchema(resParameter, definitionMap));
							resourceParamList.add(resParameter);
						}
					}
					resource.setMethod(method);
					resource.setParameters(resourceParamList);
					resource.setOperationId(
							ope.getOperationId() == null ? Helper.getRandomNumberString() : ope.getOperationId());
					resource.setResponses(apiResponses);
					resourceList.add(resource);
				}
			}
			apiDetail.setHttpSchema(swagger.getSchemes().get(0).name());
			apiDetail.setHostName(swagger.getHost());
			apiDetail.setBasePath(swagger.getBasePath());
			apiDetail.setResourceList(resourceList);
			apiDetail.setErrors(apiErrors);
			apiDetail.setApiTitle(swagger.getInfo().getTitle());
			return apiDetail;
		}

	}

	/**
	 * 
	 * @param swaggerPath
	 * @return
	 */
	private ApiDetail getApiDetailOas3(String swaggerPath) {

		// Parsed information of the swagger
		ApiDetail apiDetail = new ApiDetail();

		// Get file extension
		String fileExt = Helper.getFileExtension(swaggerPath);

		List<ApiError> apiErrorList = null;

		OpenAPI openApi = null;

		// Swagger swagger = null;

		if (fileExt.length() == 0) {
			apiErrorList = new ArrayList<ApiError>();
			ApiError apiError = new ApiError();
			apiError.setErrorCode(MISSING_FILE_EXT_CODE);
			apiError.setErrorDescription(MISSING_FILE_EXT_DESC);
			apiErrorList.add(apiError);
			apiDetail.setErrors(apiErrorList);
			logger.error(MISSING_FILE_EXT_CODE + " " + MISSING_FILE_EXT_DESC);
			return apiDetail;
		} else {
			if (fileExt.equalsIgnoreCase("json")) {
				openApi = new OpenAPIV3Parser().read(swaggerPath);
			} else if (fileExt.equalsIgnoreCase("yaml") || fileExt.equalsIgnoreCase("yml")) {
				String jsonString = Helper.convertYamlToJson(swaggerPath);
				logger.info("JSON value of yaml definition \n" + jsonString);
				openApi = new OpenAPIV3Parser().read(swaggerPath);
			} else {
				apiErrorList = new ArrayList<ApiError>();
				ApiError apiError = new ApiError();
				apiError.setErrorCode(INVALID_FILE_EXT_CODE);
				apiError.setErrorDescription(String.format(INVALID_FILE_EXT_DESC, fileExt));
				apiErrorList.add(apiError);
				apiDetail.setErrors(apiErrorList);
				logger.error(INVALID_FILE_EXT_CODE + " " + String.format(INVALID_FILE_EXT_DESC, fileExt));
				return apiDetail;
			}
		}

		List<ApiError> apiErrors = getErrorsOas3(openApi);
		if (apiErrors.size() > 0) {
			logger.info("Error exists in the swagger file.");
			for (ApiError apiError : apiErrors) {
				logger.info(apiError.getErrorDescription());
			}
			apiDetail.setErrors(apiErrors);
			return apiDetail;
		} else {
			// List of resources of the API
			List<Resource> resourceList = new ArrayList<Resource>();

			// List of the path
			//List<String> pathList = new ArrayList<String>();
			// Get the model definitions defined in the swagger
			// Map<String, Definition> definitionMap = Helper.getDefinitions(swagger);

			Resource resource = null;

			List<ApiResponse> apiResponses;

			List<io.swagger.v3.oas.models.parameters.Parameter> paramList = null;

			List<ResourceParameter> resourceParamList = null;

			// Get path information
			// Map<String, Path> paths = swagger.getPaths();
			Paths paths = openApi.getPaths();
			
			//get components 
			Components components = openApi.getComponents();
			
			//System.out.println(components);
			
			Map<String, Schema> schema = components.getSchemas();
			 
			Map<String, io.swagger.v3.oas.models.parameters.Parameter> parameters = components.getParameters();
			 
			// Fetching paths value
			for (Map.Entry<String, PathItem> entry : paths.entrySet()) {
				PathItem path = entry.getValue();
				String pathKey = entry.getKey();
				Map<io.swagger.v3.oas.models.PathItem.HttpMethod, io.swagger.v3.oas.models.Operation> pathMap = path
						.readOperationsMap();
				for (Map.Entry<io.swagger.v3.oas.models.PathItem.HttpMethod, io.swagger.v3.oas.models.Operation> entry1 : pathMap
						.entrySet()) {
					apiResponses = new ArrayList<>();
					resource = new Resource();
					io.swagger.v3.oas.models.Operation operation = entry1.getValue();

					io.swagger.v3.oas.models.responses.ApiResponses responses = operation.getResponses();

					//get request body in case of create/update/delete operations
					
					RequestBody requestBody = operation.getRequestBody();
					
					for (Map.Entry<String, io.swagger.v3.oas.models.responses.ApiResponse> response : responses
							.entrySet()) {
						ApiResponse apiResponse = new ApiResponse();
						apiResponse.setResponseKey(response.getKey());
						String resDesc = response.getValue().getDescription();
						apiResponse.setResponseDescription(resDesc != null ? resDesc : "");
						io.swagger.v3.oas.models.media.Content content = response.getValue().getContent();
						if (content != null) {
							apiResponse.setResponseContentType(content.keySet().iterator().next());
						}
						apiResponses.add(apiResponse);
					}

					paramList = operation.getParameters();

					resourceParamList = new ArrayList<ResourceParameter>();

					if (paramList != null) {
						for (io.swagger.v3.oas.models.parameters.Parameter p : paramList) {
							ResourceParameter resParameter = Helper.getResourceParameter(p);
							// resParameter.setJsonSchema(Helper.getResourceBodySchema(resParameter,
							// definitionMap));
							resourceParamList.add(resParameter);
						}
					}
					// setting resource properties
					resource.setPath(pathKey);
					resource.setMethod(entry1.getKey().name());
					resource.setParameters(resourceParamList);
					if(requestBody!=null)
						resource.setBody(UPDATE_ME);
					resource.setOperationId(operation.getOperationId() == null ? Helper.getRandomNumberString()
							: operation.getOperationId());
					resource.setResponses(apiResponses);
					resourceList.add(resource);
				}
			}

			Server server = Helper.getServer(openApi.getServers().get(0).getUrl());
			apiDetail.setHttpSchema(server.getHttpSchema());
			apiDetail.setHostName(server.getHost());
			apiDetail.setPort(server.getPort());
			apiDetail.setResourceList(resourceList);
			apiDetail.setErrors(apiErrors);
			apiDetail.setApiTitle(openApi.getInfo().getTitle());
			return apiDetail;
		}

	}

	/**
	 * 
	 * @param swaggerPath
	 * @return
	 */
	public ApiDetail getApiDetail(String swaggerPath, String version) {
		if (version.equals("2")) {
			return getApiDetailOas2(swaggerPath);
		} else if (version.equals("3")) {
			return getApiDetailOas3(swaggerPath);
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param swagger
	 * @return
	 */
	private List<ApiError> getErrorsOas2(Swagger swagger) {
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		if (null == swagger.getHost()) {
			apiErrors.add(Helper.getApiError(ERROR_HOST));
		}
		if (null == swagger.getBasePath()) {
			apiErrors.add(Helper.getApiError(ERROR_BASEPATH));
		}
		if (null == swagger.getSchemes()) {
			apiErrors.add(Helper.getApiError(ERROR_HTTP_SCHEME));
		}
		return apiErrors;
	}

	
	/**
	 * 
	 * @param swagger
	 * @return
	 */
	private List<ApiError> getErrorsOas3(OpenAPI swagger) {
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		Server server = Helper.getServer(swagger.getServers().get(0).getUrl());
		if (null == server.getHost()) {
			apiErrors.add(Helper.getApiError(ERROR_HOST));
		}

		if (null == server.getHttpSchema()) {
			apiErrors.add(Helper.getApiError(ERROR_HTTP_SCHEME));
		}
		return apiErrors;
	}
}
