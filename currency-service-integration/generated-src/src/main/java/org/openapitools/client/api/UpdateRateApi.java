package org.openapitools.client.api;

import org.openapitools.client.ApiClient;

import org.openapitools.client.model.UpdateRate200ResponseDto;
import org.openapitools.client.model.UpdateRateDto;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
@Component("org.openapitools.client.api.UpdateRateApi")
public class UpdateRateApi {
    private ApiClient apiClient;

    public UpdateRateApi() {
        this(new ApiClient());
    }

    @Autowired
    public UpdateRateApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Update specific rate for spec date
     * 
     * <p><b>200</b> - Update rate successful
     * @param updateRateDto  (optional)
     * @return UpdateRate200ResponseDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public UpdateRate200ResponseDto updateRate(UpdateRateDto updateRateDto) throws RestClientException {
        return updateRateWithHttpInfo(updateRateDto).getBody();
    }

    /**
     * Update specific rate for spec date
     * 
     * <p><b>200</b> - Update rate successful
     * @param updateRateDto  (optional)
     * @return ResponseEntity&lt;UpdateRate200ResponseDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<UpdateRate200ResponseDto> updateRateWithHttpInfo(UpdateRateDto updateRateDto) throws RestClientException {
        Object localVarPostBody = updateRateDto;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
         };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<UpdateRate200ResponseDto> localReturnType = new ParameterizedTypeReference<UpdateRate200ResponseDto>() {};
        return apiClient.invokeAPI("/api/v1/rates", HttpMethod.POST, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
}
