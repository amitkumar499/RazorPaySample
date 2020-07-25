package com.practice.RazorPaySample.response;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

public class CreatePaymentOrderResponse extends ServiceResponse {

    private String transactionId;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "CreatePaymentOrderResponse{" +
                "transactionId='" + transactionId + '\'' +
                '}';
    }
}
