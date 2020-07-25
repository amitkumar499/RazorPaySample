package com.practice.RazorPaySample.service;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

import com.practice.RazorPaySample.request.CreatePaymentOrderRequest;
import com.practice.RazorPaySample.request.MakePaymentRequest;
import com.practice.RazorPaySample.response.CreatePaymentOrderResponse;
import com.practice.RazorPaySample.response.MakePaymentResponse;

public interface PaymentService {
    MakePaymentResponse makePayment(MakePaymentRequest request);

    CreatePaymentOrderResponse createPaymentOrder(CreatePaymentOrderRequest request);
}
