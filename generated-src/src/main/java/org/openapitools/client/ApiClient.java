package org.openapitools.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.openapitools.jackson.nullable.JsonNullableModule;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.time.OffsetDateTime;

import org.openapitools.client.auth.Authentication;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
@Component("org.openapitools.client.ApiClient")
public class ApiClient extends JavaTimeFormatter {
    public enum CollectionFormat {
        CSV(","), TSV("\t"), SSV(" "), PIPES("|"), MULTI(null);

        private final String separator;

        private CollectionFormat(String separator) {
            this.separator = separator;
        }

        private String collectionToString(Collection<?> collection) {
            return StringUtils.collectionToDelimitedString(collection, separator);
        }
    }

    private boolean debugging = false;

    private HttpHeaders defaultHeaders = new HttpHeaders();
    private MultiValueMap<String, String> defaultCookies = new LinkedMultiValueMap<String, String>();

    private String basePath = "http://localhost";

    private RestTemplate restTemplate;

    private Map<String, Authentication> authentications;

    private DateFormat dateFormat;

    public ApiClient() {
        this.restTemplate = buildRestTemplate();
        init();
    }

    @Autowired
    public ApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        init();
    }

    protected void init() {
        // Use RFC3339 format for date and datetime.
        // See http://xml2rfc.ietf.org/public/rfc/html/rfc3339.html#anchor14
        this.dateFormat = new RFC3339DateFormat();

        // Use UTC as the default time zone.
        this.dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        // Set default User-Agent.
        setUserAgent("Java-SDK");

        // Setup authentications (key: authentication name, value: authentication).
        authentications = new HashMap<String, Authentication>();
        // Prevent the authentications from being modified.
        authentications = Collections.unmodifiableMap(authentications);
    }

    /**
     * Get the current base path
     *
     * @return String the base path
     */
    public String getBasePath() {
        return basePath;
    }

    /**
     * Set the base path, which should include the host
     *
     * @param basePath the base path
     * @return ApiClient this client
     */
    public ApiClient setBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    /**
     * Get authentications (key: authentication name, value: authentication).
     *
     * @return Map the currently configured authentication types
     */
    public Map<String, Authentication> getAuthentications() {
        return authentications;
    }

    /**
     * Get authentication for the given name.
     *
     * @param authName The authentication name
     * @return The authentication, null if not found
     */
    public Authentication getAuthentication(String authName) {
        return authentications.get(authName);
    }





    /**
     * Set the User-Agent header's value (by adding to the default header map).
     *
     * @param userAgent the user agent string
     * @return ApiClient this client
     */
    public ApiClient setUserAgent(String userAgent) {
        addDefaultHeader("User-Agent", userAgent);
        return this;
    }

    /**
     * Add a default header.
     *
     * @param name  The header's name
     * @param value The header's value
     * @return ApiClient this client
     */
    public ApiClient addDefaultHeader(String name, String value) {
        if (defaultHeaders.containsKey(name)) {
            defaultHeaders.remove(name);
        }
        defaultHeaders.add(name, value);
        return this;
    }

    /**
     * Add a default cookie.
     *
     * @param name  The cookie's name
     * @param value The cookie's value
     * @return ApiClient this client
     */
    public ApiClient addDefaultCookie(String name, String value) {
        if (defaultCookies.containsKey(name)) {
            defaultCookies.remove(name);
        }
        defaultCookies.add(name, value);
        return this;
    }

    public void setDebugging(boolean debugging) {
        List<ClientHttpRequestInterceptor> currentInterceptors = this.restTemplate.getInterceptors();
        if (debugging) {
            if (currentInterceptors == null) {
                currentInterceptors = new ArrayList<ClientHttpRequestInterceptor>();
            }
            ClientHttpRequestInterceptor interceptor = new ApiClientHttpRequestInterceptor();
            currentInterceptors.add(interceptor);
            this.restTemplate.setInterceptors(currentInterceptors);
        } else {
            if (currentInterceptors != null && !currentInterceptors.isEmpty()) {
                Iterator<ClientHttpRequestInterceptor> iter = currentInterceptors.iterator();
                while (iter.hasNext()) {
                    ClientHttpRequestInterceptor interceptor = iter.next();
                    if (interceptor instanceof ApiClientHttpRequestInterceptor) {
                        iter.remove();
                    }
                }
                this.restTemplate.setInterceptors(currentInterceptors);
            }
        }
        this.debugging = debugging;
    }

    /**
     * Check that whether debugging is enabled for this API client.
     * @return boolean true if this client is enabled for debugging, false otherwise
     */
    public boolean isDebugging() {
        return debugging;
    }

    /**
     * Get the date format used to parse/format date parameters.
     * @return DateFormat format
     */
    public DateFormat getDateFormat() {
        return dateFormat;
    }

    /**
     * Set the date format used to parse/format date parameters.
     * @param dateFormat Date format
     * @return API client
     */
    public ApiClient setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }

    /**
     * Parse the given string into Date object.
     *
     * @param str the string to parse
     * @return the Date parsed from the string
     */
    public Date parseDate(String str) {
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Format the given Date object into string.
     *
     * @param date the date to format
     * @return the formatted date as string
     */
    public String formatDate(Date date) {
        return dateFormat.format(date);
    }

    /**
     * Format the given parameter object into string.
     *
     * @param param the object to convert
     * @return String the parameter represented as a String
     */
    public String parameterToString(Object param) {
        if (param == null) {
            return "";
        } else if (param instanceof Date) {
            return formatDate( (Date) param);
        } else if (param instanceof OffsetDateTime) {
            return formatOffsetDateTime((OffsetDateTime) param);
        } else if (param instanceof Collection) {
            StringBuilder b = new StringBuilder();
            for (Object o : (Collection<?>) param) {
                if (b.length() > 0) {
                    b.append(",");
                }
                b.append(String.valueOf(o));
            }
            return b.toString();
        } else {
            return String.valueOf(param);
        }
    }

    /**
    * Formats the specified collection path parameter to a string value.
    *
    * @param collectionFormat The collection format of the parameter.
    * @param values The values of the parameter.
    * @return String representation of the parameter
    */
    public String collectionPathParameterToString(CollectionFormat collectionFormat, Collection<?> values) {
        // create the value based on the collection format
        if (CollectionFormat.MULTI.equals(collectionFormat)) {
            // not valid for path params
            return parameterToString(values);
        }

        // collectionFormat is assumed to be "csv" by default
        if (collectionFormat == null) {
            collectionFormat = CollectionFormat.CSV;
        }

        return collectionFormat.collectionToString(values);
    }

    /**
     * Converts a parameter to a {@link MultiValueMap} for use in REST requests
     *
     * @param collectionFormat The format to convert to
     * @param name The name of the parameter
     * @param value The parameter's value
     * @return a Map containing the String value(s) of the input parameter
     */
    public MultiValueMap<String, String> parameterToMultiValueMap(CollectionFormat collectionFormat, String name, Object value) {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        if (name == null || name.isEmpty() || value == null) {
            return params;
        }

        if (collectionFormat == null) {
            collectionFormat = CollectionFormat.CSV;
        }

        if (value instanceof Map) {
            @SuppressWarnings("unchecked")
            final Map<String, Object> valuesMap = (Map<String, Object>) value;
            for (final Entry<String, Object> entry : valuesMap.entrySet()) {
                params.add(entry.getKey(), parameterToString(entry.getValue()));
            }
            return params;
        }

        Collection<?> valueCollection = null;
        if (value instanceof Collection) {
            valueCollection = (Collection<?>) value;
        } else {
            params.add(name, parameterToString(value));
            return params;
        }

        if (valueCollection.isEmpty()) {
            return params;
        }

        if (collectionFormat.equals(CollectionFormat.MULTI)) {
            for (Object item : valueCollection) {
                params.add(name, parameterToString(item));
            }
            return params;
        }

        List<String> values = new ArrayList<String>();
        for (Object o : valueCollection) {
            values.add(parameterToString(o));
        }
        params.add(name, collectionFormat.collectionToString(values));

        return params;
    }

   /**
    * Check if the given {@code String} is a JSON MIME.
    *
    * @param mediaType the input MediaType
    * @return boolean true if the MediaType represents JSON, false otherwise
    */
    public boolean isJsonMime(String mediaType) {
        // "* / *" is default to JSON
        if ("*/*".equals(mediaType)) {
            return true;
        }

        try {
            return isJsonMime(MediaType.parseMediaType(mediaType));
        } catch (InvalidMediaTypeException e) {
        }
        return false;
    }

    /**
     * Check if the given MIME is a JSON MIME.
     * JSON MIME examples:
     *     application/json
     *     application/json; charset=UTF8
     *     APPLICATION/JSON
     *
     * @param mediaType the input MediaType
     * @return boolean true if the MediaType represents JSON, false otherwise
     */
    public boolean isJsonMime(MediaType mediaType) {
        return mediaType != null && (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType) || mediaType.getSubtype().matches("^.*\\+json[;]?\\s*$"));
    }

   /**
    * Check if the given {@code String} is a Problem JSON MIME (RFC-7807).
    *
    * @param mediaType the input MediaType
    * @return boolean true if the MediaType represents Problem JSON, false otherwise
    */
    public boolean isProblemJsonMime(String mediaType) {
        return "application/problem+json".equalsIgnoreCase(mediaType);
    }

    /**
     * Select the Accept header's value from the given accepts array:
     *     if JSON exists in the given array, use it;
     *     otherwise use all of them (joining into a string)
     *
     * @param accepts The accepts array to select from
     * @return List The list of MediaTypes to use for the Accept header
     */
    public List<MediaType> selectHeaderAccept(String[] accepts) {
        if (accepts.length == 0) {
            return null;
        }
        for (String accept : accepts) {
            MediaType mediaType = MediaType.parseMediaType(accept);
            if (isJsonMime(mediaType) && !isProblemJsonMime(accept)) {
                return Collections.singletonList(mediaType);
            }
        }
        return MediaType.parseMediaTypes(StringUtils.arrayToCommaDelimitedString(accepts));
    }

    /**
     * Select the Content-Type header's value from the given array:
     *     if JSON exists in the given array, use it;
     *     otherwise use the first one of the array.
     *
     * @param contentTypes The Content-Type array to select from
     * @return MediaType The Content-Type header to use. If the given array is empty, JSON will be used.
     */
    public MediaType selectHeaderContentType(String[] contentTypes) {
        if (contentTypes.length == 0) {
            return MediaType.APPLICATION_JSON;
        }
        for (String contentType : contentTypes) {
            MediaType mediaType = MediaType.parseMediaType(contentType);
            if (isJsonMime(mediaType)) {
                return mediaType;
            }
        }
        return MediaType.parseMediaType(contentTypes[0]);
    }

    /**
     * Select the body to use for the request
     *
     * @param obj the body object
     * @param formParams the form parameters
     * @param contentType the content type of the request
     * @return Object the selected body
     */
    protected Object selectBody(Object obj, MultiValueMap<String, Object> formParams, MediaType contentType) {
        boolean isForm = MediaType.MULTIPART_FORM_DATA.isCompatibleWith(contentType) || MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(contentType);
        return isForm ? formParams : obj;
    }

    /**
     * Expand path template with variables
     *
     * @param pathTemplate path template with placeholders
     * @param variables variables to replace
     * @return path with placeholders replaced by variables
     */
    public String expandPath(String pathTemplate, Map<String, Object> variables) {
        return restTemplate.getUriTemplateHandler().expand(pathTemplate, variables).toString();
    }

    /**
     * Include queryParams in uriParams taking into account the paramName
     *
     * @param queryParams The query parameters
     * @param uriParams The path parameters
     * return templatized query string
     */
    public String generateQueryUri(MultiValueMap<String, String> queryParams, Map<String, Object> uriParams) {
        StringBuilder queryBuilder = new StringBuilder();
        queryParams.forEach((name, values) -> {
            try {
                final String encodedName = URLEncoder.encode(name.toString(), "UTF-8");
                if (CollectionUtils.isEmpty(values)) {
                    if (queryBuilder.length() != 0) {
                        queryBuilder.append('&');
                    }
                    queryBuilder.append(encodedName);
                } else {
                    int valueItemCounter = 0;
                    for (Object value : values) {
                        if (queryBuilder.length() != 0) {
                            queryBuilder.append('&');
                        }
                        queryBuilder.append(encodedName);
                        if (value != null) {
                            String templatizedKey = encodedName + valueItemCounter++;
                            uriParams.put(templatizedKey, value.toString());
                            queryBuilder.append('=').append("{").append(templatizedKey).append("}");
                        }
                    }
                }
            } catch (UnsupportedEncodingException e) {

            }
        });
        return queryBuilder.toString();

    }

    /**
     * Invoke API by sending HTTP request with the given options.
     *
     * @param <T> the return type to use
     * @param path The sub-path of the HTTP URL
     * @param method The request method
     * @param pathParams The path parameters
     * @param queryParams The query parameters
     * @param body The request body object
     * @param headerParams The header parameters
     * @param cookieParams The cookie parameters
     * @param formParams The form parameters
     * @param accept The request's Accept header
     * @param contentType The request's Content-Type header
     * @param authNames The authentications to apply
     * @param returnType The return type into which to deserialize the response
     * @return ResponseEntity&lt;T&gt; The response of the chosen type
     */
    public <T> ResponseEntity<T> invokeAPI(String path, HttpMethod method, Map<String, Object> pathParams, MultiValueMap<String, String> queryParams, Object body, HttpHeaders headerParams, MultiValueMap<String, String> cookieParams, MultiValueMap<String, Object> formParams, List<MediaType> accept, MediaType contentType, String[] authNames, ParameterizedTypeReference<T> returnType) throws RestClientException {
        updateParamsForAuth(authNames, queryParams, headerParams, cookieParams);

        Map<String,Object> uriParams = new HashMap<>();
        uriParams.putAll(pathParams);

        String finalUri = path;

        if (queryParams != null && !queryParams.isEmpty()) {
            //Include queryParams in uriParams taking into account the paramName
            String queryUri = generateQueryUri(queryParams, uriParams);
            //Append to finalUri the templatized query string like "?param1={param1Value}&.......
            finalUri += "?" + queryUri;
        }
        String expandedPath = this.expandPath(finalUri, uriParams);
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(basePath).path(expandedPath);

        URI uri;
        try {
            uri = new URI(builder.build().toUriString());
        } catch (URISyntaxException ex)  {
            throw new RestClientException("Could not build URL: " + builder.toUriString(), ex);
        }

        final BodyBuilder requestBuilder = RequestEntity.method(method, uri);
        if (accept != null) {
            requestBuilder.accept(accept.toArray(new MediaType[accept.size()]));
        }
        if (contentType != null) {
            requestBuilder.contentType(contentType);
        }

        addHeadersToRequest(headerParams, requestBuilder);
        addHeadersToRequest(defaultHeaders, requestBuilder);
        addCookiesToRequest(cookieParams, requestBuilder);
        addCookiesToRequest(defaultCookies, requestBuilder);

        RequestEntity<Object> requestEntity = requestBuilder.body(selectBody(body, formParams, contentType));

        ResponseEntity<T> responseEntity = restTemplate.exchange(requestEntity, returnType);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity;
        } else {
            // The error handler built into the RestTemplate should handle 400 and 500 series errors.
            throw new RestClientException("API returned " + responseEntity.getStatusCode() + " and it wasn't handled by the RestTemplate error handler");
        }
    }

    /**
     * Add headers to the request that is being built
     * @param headers The headers to add
     * @param requestBuilder The current request
     */
    protected void addHeadersToRequest(HttpHeaders headers, BodyBuilder requestBuilder) {
        for (Entry<String, List<String>> entry : headers.entrySet()) {
            List<String> values = entry.getValue();
            for (String value : values) {
                if (value != null) {
                    requestBuilder.header(entry.getKey(), value);
                }
            }
        }
    }

    /**
     * Add cookies to the request that is being built
     *
     * @param cookies        The cookies to add
     * @param requestBuilder The current request
     */
    protected void addCookiesToRequest(MultiValueMap<String, String> cookies, BodyBuilder requestBuilder) {
        if (!cookies.isEmpty()) {
            requestBuilder.header("Cookie", buildCookieHeader(cookies));
        }
    }

    /**
     * Build cookie header. Keeps a single value per cookie (as per <a href="https://tools.ietf.org/html/rfc6265#section-5.3">
     * RFC6265 section 5.3</a>).
     *
     * @param cookies map all cookies
     * @return header string for cookies.
     */
    private String buildCookieHeader(MultiValueMap<String, String> cookies) {
        final StringBuilder cookieValue = new StringBuilder();
        String delimiter = "";
        for (final Map.Entry<String, List<String>> entry : cookies.entrySet()) {
            final String value = entry.getValue().get(entry.getValue().size() - 1);
            cookieValue.append(String.format("%s%s=%s", delimiter, entry.getKey(), value));
            delimiter = "; ";
        }
        return cookieValue.toString();
    }

    /**
     * Build the RestTemplate used to make HTTP requests.
     * @return RestTemplate
     */
    protected RestTemplate buildRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // This allows us to read the response more than once - Necessary for debugging.
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(restTemplate.getRequestFactory()));

        // disable default URL encoding
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory();
        uriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        restTemplate.setUriTemplateHandler(uriBuilderFactory);
        return restTemplate;
    }

    /**
     * Update query and header parameters based on authentication settings.
     *
     * @param authNames The authentications to apply
     * @param queryParams The query parameters
     * @param headerParams The header parameters
     */
    protected void updateParamsForAuth(String[] authNames, MultiValueMap<String, String> queryParams, HttpHeaders headerParams, MultiValueMap<String, String> cookieParams) {
        for (String authName : authNames) {
            Authentication auth = authentications.get(authName);
            if (auth == null) {
                throw new RestClientException("Authentication undefined: " + authName);
            }
            auth.applyToParams(queryParams, headerParams, cookieParams);
        }
    }

    private class ApiClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
        private final Log log = LogFactory.getLog(ApiClientHttpRequestInterceptor.class);

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            logRequest(request, body);
            ClientHttpResponse response = execution.execute(request, body);
            logResponse(response);
            return response;
        }

        private void logRequest(HttpRequest request, byte[] body) throws UnsupportedEncodingException {
            log.info("URI: " + request.getURI());
            log.info("HTTP Method: " + request.getMethod());
            log.info("HTTP Headers: " + headersToString(request.getHeaders()));
            log.info("Request Body: " + new String(body, StandardCharsets.UTF_8));
        }

        private void logResponse(ClientHttpResponse response) throws IOException {
            log.info("HTTP Status Code: " + response.getRawStatusCode());
            log.info("Status Text: " + response.getStatusText());
            log.info("HTTP Headers: " + headersToString(response.getHeaders()));
            log.info("Response Body: " + bodyToString(response.getBody()));
        }

        private String headersToString(HttpHeaders headers) {
            if(headers == null || headers.isEmpty()) {
                return "";
            }
            StringBuilder builder = new StringBuilder();
            for (Entry<String, List<String>> entry : headers.entrySet()) {
                builder.append(entry.getKey()).append("=[");
                for (String value : entry.getValue()) {
                    builder.append(value).append(",");
                }
                builder.setLength(builder.length() - 1); // Get rid of trailing comma
                builder.append("],");
            }
            builder.setLength(builder.length() - 1); // Get rid of trailing comma
            return builder.toString();
        }

        private String bodyToString(InputStream body) throws IOException {
            StringBuilder builder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(body, StandardCharsets.UTF_8));
            String line = bufferedReader.readLine();
            while (line != null) {
                builder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            return builder.toString();
        }
    }
}
