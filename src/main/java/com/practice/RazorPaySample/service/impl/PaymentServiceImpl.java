package com.practice.RazorPaySample.service.impl;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

import com.practice.RazorPaySample.external.RazorPayExternalService;
import com.practice.RazorPaySample.request.CreatePaymentOrderRequest;
import com.practice.RazorPaySample.request.MakePaymentRequest;
import com.practice.RazorPaySample.response.CreatePaymentOrderResponse;
import com.practice.RazorPaySample.response.MakePaymentResponse;
import com.practice.RazorPaySample.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private RazorPayExternalService razorPayExternalService;

    @Autowired
    private Environment environment;

    @Override
    public MakePaymentResponse makePayment(MakePaymentRequest request){
        MakePaymentResponse response=new MakePaymentResponse();

        return response;
    }

    @Override
    public CreatePaymentOrderResponse createPaymentOrder(CreatePaymentOrderRequest request) {
        String webserviceUrl=environment.getProperty("razorpay.web.service.url");
        String requestJson="{\n" +
                "  \"amount\": 50000,\n" +
                "  \"currency\": \"INR\",\n" +
                "  \"receipt\": \"receipt#1\",\n" +
                "  \"payment_capture\": 1\n" +
                "}";
        try {
            System.out.println(razorPayExternalService.postRequestToPg("payment",webserviceUrl,requestJson,7000).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
