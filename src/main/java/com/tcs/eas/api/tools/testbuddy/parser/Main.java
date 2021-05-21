package com.tcs.eas.api.tools.testbuddy.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.tcs.eas.api.tools.testbuddy.constant.Constant;
import com.tcs.eas.api.tools.testbuddy.model.ApiDetail;
import com.tcs.eas.api.tools.testbuddy.model.ApiError;
import com.tcs.eas.api.tools.testbuddy.model.ApiResponse;
import com.tcs.eas.api.tools.testbuddy.model.Definition;
import com.tcs.eas.api.tools.testbuddy.model.Resource;
import com.tcs.eas.api.tools.testbuddy.model.ResourceParameter;
import com.tcs.eas.api.tools.testbuddy.util.Helper;

import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.RefResponse;
import io.swagger.models.Response;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.properties.Property;
import io.swagger.parser.SwaggerParser;

/**
 * 
 * @author 44745
 *
 */
public class Main implements Constant {

	// Logger for logging
	final static Logger logger = Logger.getLogger(Main.class);

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Swagger parsing...");
		String swaggerPath = "C:\\manish\\cma-works\\testbuddy\\pet2.json";
		
		String swaggerPath1 = "C:\\manish\\cma-works\\testbuddy\\swagger-def-rec.yaml";
		
		String yamlPath = "C:\\manish\\cma-works\\testbuddy\\swagger-test.yaml";
		Main main = new Main();
		ApiDetail apiDetail = main.getApiDetail(swaggerPath);
		main.printResults(apiDetail);
		//System.out.println(main.getFileExtension("C:\\manish\\cma-works\\testbuddy\\pet2").length());
	}

	/**
	 * 
	 * @param swaggerPath
	 * @return
	 */
	public ApiDetail getApiDetail(String swaggerPath) {
		// Parsed information of the swagger
		ApiDetail apiDetail = new ApiDetail();
		
		//Get file extension
		String fileExt = getFileExtension(swaggerPath);
		
		List<ApiError> apiErrorList = null;
		
		Swagger swagger = null;
		
		if(fileExt.length() == 0) {
			apiErrorList = new ArrayList<ApiError>();
			ApiError apiError = new ApiError();
			apiError.setErrorCode(MISSING_FILE_EXT_CODE);
			apiError.setErrorDescription(MISSING_FILE_EXT_DESC);
			apiErrorList.add(apiError);
			apiDetail.setErrors(apiErrorList);
			logger.error(MISSING_FILE_EXT_CODE+" "+ MISSING_FILE_EXT_DESC);
			return apiDetail;
		}else {
			if(fileExt.equalsIgnoreCase("json")) {
				swagger = new SwaggerParser().read(swaggerPath);
			}else if(fileExt.equalsIgnoreCase("yaml") || fileExt.equalsIgnoreCase("yml")) {
				String jsonString = Helper.convertYamlToJson(swaggerPath);
				logger.info("JSON value of yaml definition \n"+jsonString);
				swagger = new SwaggerParser().parse(jsonString);
			}else {
				apiErrorList = new ArrayList<ApiError>();
				ApiError apiError = new ApiError();
				apiError.setErrorCode(INVALID_FILE_EXT_CODE);
				apiError.setErrorDescription(String.format(INVALID_FILE_EXT_DESC,fileExt));
				apiErrorList.add(apiError);
				apiDetail.setErrors(apiErrorList);
				logger.error(INVALID_FILE_EXT_CODE+" "+ String.format(INVALID_FILE_EXT_DESC,fileExt));
				return apiDetail;
			}
		}	

		List<ApiError> apiErrors = getErrors(swagger);
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
					Map<String, Response> responses = ope.getResponses();
					
					for (Map.Entry<String, Response>  response : responses.entrySet()) {
						ApiResponse apiResponse = new ApiResponse();   
						apiResponse.setResponseKey(response.getKey());
						String resDesc = response.getValue().getDescription();
						apiResponse.setResponseDescription(resDesc!=null ? resDesc : "");
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
					resource.setOperationId(ope.getOperationId()== null ? Helper.getRandomNumberString() : ope.getOperationId());
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
	 * @param filePath
	 * @return
	 */
	private String getFileExtension(String filePath) {
		return FilenameUtils.getExtension(filePath);
	}
	/**
	 * 
	 * @param swagger
	 * @return
	 */
	private List<ApiError> getErrors(Swagger swagger) {
		List<ApiError> apiErrors = new ArrayList<ApiError>();
		ApiError apiError = null;
		if (null == swagger.getHost()) {
			apiError = new ApiError();
			apiError.setErrorCode(MISSING_FIELD_CODE);
			apiError.setErrorDescription(String.format(MISSING_FIELD_DESC, "Host"));
			apiErrors.add(apiError);
		}

		if (null == swagger.getBasePath()) {
			apiError = new ApiError();
			apiError.setErrorCode(MISSING_FIELD_CODE);
			apiError.setErrorDescription(String.format(MISSING_FIELD_DESC, "Basepath"));
			apiErrors.add(apiError);
		}

		if (null == swagger.getSchemes()) {
			apiError = new ApiError();
			apiError.setErrorCode(MISSING_FIELD_CODE);
			apiError.setErrorDescription(String.format(MISSING_FIELD_DESC, "HTTP Schema"));
			apiErrors.add(apiError);
		}
		return apiErrors;
	}
	
	/**
	 * For testing only
	 * @param apiDetail
	 */
	private void printResults(ApiDetail apiDetail) {
		List<ApiError> apiErrors = apiDetail.getErrors();
		if(apiErrors.size()>0) {
			System.out.println("Something went wrong...");
			for(ApiError apiError : apiErrors) {
				System.out.println(apiError.getErrorCode()+"  " + apiError.getErrorDescription());
				return;
			}
			
		}
		System.out.println("HTTP Schema: "+apiDetail.getHttpSchema());
		System.out.println("Host: "+apiDetail.getHostName());
		System.out.println("Basepath: "+apiDetail.getBasePath());
		for (Resource r : apiDetail.getResourceList()) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("Resource ==>"+r.getPath());
			System.out.println(
					"Method=" + r.getMethod() + " Path=" + r.getPath() + " Parameters=" + r.getParameters().size());
			List<ResourceParameter> paramList = r.getParameters();
			System.out.println("Parameter List======>");
			for (ResourceParameter rp : paramList) {
				System.out.println("................................");
				System.out.println("ParamName=" + rp.getName() +"\nDesc=" + rp.getDescription() + "\nFormat=" + rp.getFormat() + "\nIn=" + rp.getIn()
						+ "\nJSONSchema=" + rp.getJsonSchema()  +"\nType="+rp.getType()+"\nRequired="+rp.isRequired());
				
			}
			System.out.println("*******************************************");
		}

	}
}
