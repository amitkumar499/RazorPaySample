package com.practice.RazorPaySample.request;
/* 
Created by amit.chaurasia 
on 7/27/20 
*/

public class RazorPayCapturePaymentRequest {

    private Integer amount;
    private String currency;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "RazorPayCapturePaymentRequest{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
