package com.practice.RazorPaySample.constants;
/* 
Created by amit.chaurasia 
on 7/26/20 
*/

public enum PaymentStatus {

    CREATED("crd"),
    AUTHORIZED("authorized"),
    CAPTURED("captured"),
    FAILED("failed");

    private String status;

    private PaymentStatus(String status) {
        this.status = status;
    }

    public String status() {
        return status;
    }
}
