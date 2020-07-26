package com.practice.RazorPaySample.response;
/* 
Created by amit.chaurasia 
on 7/26/20 
*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RazorPayCapturePaymentResponse implements Serializable {

    private boolean captured;

    public boolean isCaptured() {
        return captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    @Override
    public String toString() {
        return "RazorPayCapturePaymentResponse{" +
                "captured=" + captured +
                '}';
    }
}
