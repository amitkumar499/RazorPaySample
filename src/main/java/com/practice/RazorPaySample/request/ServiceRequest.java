package com.practice.RazorPaySample.request;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

import java.io.Serializable;

public class ServiceRequest implements Serializable {

    private String requestProtocol;
    private String responseProtocol;

    public String getRequestProtocol() {
        return requestProtocol;
    }

    public void setRequestProtocol(String requestProtocol) {
        this.requestProtocol = requestProtocol;
    }

    public String getResponseProtocol() {
        return responseProtocol;
    }

    public void setResponseProtocol(String responseProtocol) {
        this.responseProtocol = responseProtocol;
    }
}
