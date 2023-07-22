package com.geneinsure.cqmservice.controller.payload;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

public class ApiResp {

    private String message;

    public ApiResp(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
