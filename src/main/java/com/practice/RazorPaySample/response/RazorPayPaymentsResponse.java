package com.practice.RazorPaySample.response;
/* 
Created by amit.chaurasia 
on 7/26/20 
*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RazorPayPaymentsResponse {

    private Integer amount;
    private String currency;
    private String status;
    private Long created_at;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "RazorPayPaymentsResponse{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
