package com.practice.RazorPaySample.response.sro;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

public class CreatePaymentOrderResponse extends ServiceResponse {

    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "CreatePaymentOrderResponse{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
