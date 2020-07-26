package com.practice.RazorPaySample.constants;
/* 
Created by amit.chaurasia 
on 7/26/20 
*/

public enum RazorPayAPI {

    ORDERS("orders"),
    PAYMENTS("payments"),
    CAPTURE("capture");
    private String value;

    private RazorPayAPI(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
