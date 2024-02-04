package com.kiran.restapi.utils;

public class ApiResponse {
    private String status;
    private Object data;
    private String message;

    // constructors, getters, and setters

    public ApiResponse(String status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}