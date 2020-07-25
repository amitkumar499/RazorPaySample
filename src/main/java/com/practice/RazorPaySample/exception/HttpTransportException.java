package com.practice.RazorPaySample.exception;
/* 
Created by amit.chaurasia 
on 7/25/20 
*/

public class HttpTransportException extends Exception {
    private int code;

    public HttpTransportException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpTransportException(String message) {
        super(message);
    }

    public HttpTransportException(String message, int code) {
        super(message);
        this.setCode(code);
    }

    public HttpTransportException(Throwable cause, int code) {
        super(cause);
        this.setCode(code);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
