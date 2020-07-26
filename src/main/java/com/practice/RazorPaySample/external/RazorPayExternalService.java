package com.practice.RazorPaySample.external;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

import com.practice.RazorPaySample.constants.RazorPayAPI;
import com.practice.RazorPaySample.exception.HttpTransportException;
import com.practice.RazorPaySample.request.RazorPayCreateOrderRequest;
import com.practice.RazorPaySample.request.sro.CapturePaymentRequest;
import com.practice.RazorPaySample.response.RazorPayCapturePaymentResponse;
import com.practice.RazorPaySample.response.RazorPayCreateOrderResponse;
import com.practice.RazorPaySample.response.RazorPayPaymentsResponse;
import com.practice.RazorPaySample.transport.HttpSender;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RazorPayExternalService {

    @Autowired
    private Environment environment;

    private final static String CONNECTOR = ":";
    private final static String SEPARATOR = "/";
    private final static String NEWLINE = "\n";
    private final static String AUTHORIZATION = "Authorization";

    public RazorPayCreateOrderResponse createRazorpayOrder(RazorPayCreateOrderRequest request, Integer timeout) throws HttpTransportException {
        String WEB_SERVICE_URL = environment.getProperty("razorpay.web.service.url");
        RazorPayCreateOrderResponse response = HttpSender.getInstance().executePostRequest(WEB_SERVICE_URL + RazorPayAPI.ORDERS.value(), request, RazorPayCreateOrderResponse.class, timeout, getHeaders());
        return response;
    }

    public RazorPayCapturePaymentResponse captureRazorPayPayment(CapturePaymentRequest request, Integer timeout) throws HttpTransportException {
        String WEB_SERVICE_URL = environment.getProperty("razorpay.web.service.url");
        RazorPayCapturePaymentResponse response = HttpSender.getInstance().executePostRequest(WEB_SERVICE_URL, request, RazorPayCapturePaymentResponse.class, timeout, getHeaders());
        return response;
    }

    public RazorPayPaymentsResponse getPaymentDetails(String paymentId) throws HttpTransportException {
        String WEB_SERVICE_URL = environment.getProperty("razorpay.web.service.url");
        RazorPayPaymentsResponse response = HttpSender.getInstance().executeGetRequest(WEB_SERVICE_URL + RazorPayAPI.PAYMENTS.value() + SEPARATOR + paymentId, getHeaders(), RazorPayPaymentsResponse.class);
        System.out.println(response.toString());
        return response;
    }

    public Map<String, String> getHeaders() {
        String apiKey = environment.getProperty("razorpay.api.key");
        String apiSecret = environment.getProperty("razorpay.api.secret.key");
        StringBuilder apiKeySecret = new StringBuilder(apiKey).append(CONNECTOR).append(apiSecret);
        String encodedKey = new String(Base64.encodeBase64(apiKeySecret.toString().getBytes())).replaceAll(NEWLINE, "");
        Map<String, String> headers = new HashMap<>();
        headers.put(AUTHORIZATION, String.format("Basic %s", encodedKey));
        return headers;
    }

}
