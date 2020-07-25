package com.practice.RazorPaySample.transport;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

import com.practice.RazorPaySample.exception.HttpTransportException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpSender {

    private RestTemplate restTemplate;

    private final String CONTENT_ENCODING_UTF_8 = "UTF-8";
    private static HttpSender instance = new HttpSender();

    private static final int CONNECTION_PARAMS_DIVISOR = 5;

    private HttpSender() {
        restTemplate = new RestTemplate(getClientHttpRequestFactory());
        List<HttpMessageConverter<?>> list = new ArrayList<>();
        list.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(list);
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        BasicHttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager();
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create()
                        .setConnectionManager(connectionManager).build());
        int timeout = 7000;
        clientHttpRequestFactory.setConnectTimeout(timeout);
        clientHttpRequestFactory.setReadTimeout(timeout);
        return clientHttpRequestFactory;
    }

    public static HttpSender getInstance() {
        return instance;
    }

    public <A,B> B executePostRequest(String requestUrl, A request, Class<B> responseClass, Integer timeout, Map<String, String> headers) throws HttpTransportException {
        B response = null;
        StringBuilder webServiceURL = new StringBuilder();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, headers.get(HttpHeaders.AUTHORIZATION));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        webServiceURL.append(requestUrl);
        HttpEntity<?> httpEntity = new HttpEntity<>(request, httpHeaders);
        response = restTemplate.postForObject(webServiceURL.toString(), httpEntity, responseClass);
        return response;
    }
}
