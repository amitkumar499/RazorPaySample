package com.practice.RazorPaySample.request;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RazorPayCreateOrderRequest {

    private int amount;
    private String currency;
    private String receipt;
    private String payment_capture;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getPayment_capture() {
        return payment_capture;
    }

    public void setPayment_capture(String payment_capture) {
        this.payment_capture = payment_capture;
    }
}
