package com.practice.RazorPaySample.response;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RazorPayCreateOrderResponse implements Serializable {

    private String id;
    private String entity;
    private int amount;
    private int amount_paid;
    private int amount_due;
    private String currency;
    private String receipt;
    private String status;
    private String attempts;
    private long created_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(int amount_paid) {
        this.amount_paid = amount_paid;
    }

    public int getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(int amount_due) {
        this.amount_due = amount_due;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttempts() {
        return attempts;
    }

    public void setAttempts(String attempts) {
        this.attempts = attempts;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "RazorPayCreateOrderResponse{" +
                "id='" + id + '\'' +
                ", entity='" + entity + '\'' +
                ", amount=" + amount +
                ", amount_paid=" + amount_paid +
                ", amount_due=" + amount_due +
                ", currency='" + currency + '\'' +
                ", receipt='" + receipt + '\'' +
                ", status='" + status + '\'' +
                ", attempts='" + attempts + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
