package org.openapitools.client.api;

import org.openapitools.client.ApiClient;

import org.openapitools.client.model.ConvertedRatesDto;

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
@Component("org.openapitools.client.api.ConvertRatesApi")
public class ConvertRatesApi {
    private ApiClient apiClient;

    public ConvertRatesApi() {
        this(new ApiClient());
    }

    @Autowired
    public ConvertRatesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Convert rates
     * 
     * <p><b>200</b> - Get rates successful
     * @param target Target currency (optional)
     * @param from From currency (optional)
     * @param value  (optional)
     * @return ConvertedRatesDto
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ConvertedRatesDto convertRates(String target, String from, Double value) throws RestClientException {
        return convertRatesWithHttpInfo(target, from, value).getBody();
    }

    /**
     * Convert rates
     * 
     * <p><b>200</b> - Get rates successful
     * @param target Target currency (optional)
     * @param from From currency (optional)
     * @param value  (optional)
     * @return ResponseEntity&lt;ConvertedRatesDto&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<ConvertedRatesDto> convertRatesWithHttpInfo(String target, String from, Double value) throws RestClientException {
        Object localVarPostBody = null;
        

        final MultiValueMap<String, String> localVarQueryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders localVarHeaderParams = new HttpHeaders();
        final MultiValueMap<String, String> localVarCookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> localVarFormParams = new LinkedMultiValueMap<String, Object>();

        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "target", target));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "from", from));
        localVarQueryParams.putAll(apiClient.parameterToMultiValueMap(null, "value", value));

        final String[] localVarAccepts = { 
            "application/json"
         };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = {  };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<ConvertedRatesDto> localReturnType = new ParameterizedTypeReference<ConvertedRatesDto>() {};
        return apiClient.invokeAPI("/api/v1/rates/convert", HttpMethod.GET, Collections.<String, Object>emptyMap(), localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localReturnType);
    }
}
