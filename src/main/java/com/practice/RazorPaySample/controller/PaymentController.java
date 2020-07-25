package com.practice.RazorPaySample.controller;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

import com.practice.RazorPaySample.request.CreatePaymentOrderRequest;
import com.practice.RazorPaySample.request.MakePaymentRequest;
import com.practice.RazorPaySample.response.CreatePaymentOrderResponse;
import com.practice.RazorPaySample.response.MakePaymentResponse;
import com.practice.RazorPaySample.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        CreatePaymentOrderResponse response = new CreatePaymentOrderResponse();
        response = paymentService.createPaymentOrder(request);
        return response;
    }

    @RequestMapping(value = "makePayment", method = RequestMethod.POST)
    public MakePaymentResponse makePayment(@RequestBody MakePaymentRequest request) {
        MakePaymentResponse response = new MakePaymentResponse();
        response = paymentService.makePayment(request);
        return response;
    }

    @RequestMapping(value = "createOrderAndMakePayment", method = RequestMethod.POST)
    public CreatePaymentOrderResponse createOrderAndMakePayment(@RequestBody CreatePaymentOrderRequest request) {
        CreatePaymentOrderResponse createPaymentOrderResponse = new CreatePaymentOrderResponse();

        return createPaymentOrderResponse;
    }

}
