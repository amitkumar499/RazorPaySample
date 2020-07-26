package com.practice.RazorPaySample.controller;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

import com.practice.RazorPaySample.request.sro.CapturePaymentRequest;
import com.practice.RazorPaySample.request.sro.CreatePaymentOrderRequest;
import com.practice.RazorPaySample.response.sro.CreatePaymentOrderResponse;
import com.practice.RazorPaySample.response.sro.CapturePaymentResponse;
import com.practice.RazorPaySample.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "createPaymentOrder", method = RequestMethod.POST)
    public CreatePaymentOrderResponse createPaymentOrder(@RequestBody CreatePaymentOrderRequest request) {
        CreatePaymentOrderResponse response = paymentService.createPaymentOrder(request);
        if(response==null){
            response=new CreatePaymentOrderResponse();
            response.setSuccessful(false);
        }
        return response;
    }

    @RequestMapping(value = "capturePayment", method = RequestMethod.POST)
    public CapturePaymentResponse capturePayment(@RequestBody CapturePaymentRequest request) {
        CapturePaymentResponse capturePaymentResponse= paymentService.capturePayment(request);

        return capturePaymentResponse;
    }

}
