package com.practice.RazorPaySample.response.sro;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

public class CapturePaymentResponse extends ServiceResponse{

    private boolean captured;

    public boolean isCaptured() {
        return captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    @Override
    public String toString() {
        return "CapturePaymentResponse{" +
                "captured=" + captured +
                '}';
    }
}
