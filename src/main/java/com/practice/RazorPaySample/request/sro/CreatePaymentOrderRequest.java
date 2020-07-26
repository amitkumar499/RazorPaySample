package com.practice.RazorPaySample.request.sro;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

import java.io.Serializable;

public class CreatePaymentOrderRequest implements Serializable {

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
        return "CreatePaymentOrderRequest{" +
                "amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
