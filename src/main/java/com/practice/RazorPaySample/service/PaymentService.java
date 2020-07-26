package com.practice.RazorPaySample.service;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

import com.practice.RazorPaySample.request.sro.CapturePaymentRequest;
import com.practice.RazorPaySample.request.sro.CreatePaymentOrderRequest;
import com.practice.RazorPaySample.response.sro.CapturePaymentResponse;
import com.practice.RazorPaySample.response.sro.CreatePaymentOrderResponse;

public interface PaymentService {

    CreatePaymentOrderResponse createPaymentOrder(CreatePaymentOrderRequest request);

    CapturePaymentResponse capturePayment(CapturePaymentRequest request);
}
