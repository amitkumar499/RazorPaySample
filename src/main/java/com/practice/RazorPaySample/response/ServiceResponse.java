package com.practice.RazorPaySample.response;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

import java.io.Serializable;

public class ServiceResponse implements Serializable {

    private boolean successful = true;
    private String message;
    private String code;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
