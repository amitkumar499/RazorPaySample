package com.practice.RazorPaySample.response;
/* 
Created by amit.chaurasia 
on 7/27/20 
*/

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RazorPayOrderStatusResponse implements Serializable {
    private int count;
    private List<PaymentSRO> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<PaymentSRO> getItems() {
        return items;
    }

    public void setItems(List<PaymentSRO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "RazorPayOrderStatusResponse{" +
                "count=" + count +
                ", items=" + items +
                '}';
    }
}
