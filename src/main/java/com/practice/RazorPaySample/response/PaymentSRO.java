package com.practice.RazorPaySample.response;
/* 
Created by amit.chaurasia 
on 7/27/20 
*/
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentSRO implements Serializable {
    private int amount;
    private String id;
    private String order_id;
    private String status;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PaymentSRO{" +
                "amount=" + amount +
                ", id='" + id + '\'' +
                ", order_id='" + order_id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}