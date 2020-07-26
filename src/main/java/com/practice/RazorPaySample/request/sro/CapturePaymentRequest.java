package com.practice.RazorPaySample.request.sro;
/* 
Created by amit.chaurasia 
on 7/26/20 
*/

import java.io.Serializable;

public class CapturePaymentRequest implements Serializable {

    private String orderId;
    private String transactionId;
    private String signature;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "CapturePaymentRequest{" +
                "orderId='" + orderId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
