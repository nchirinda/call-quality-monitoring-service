package com.geneinsure.cqmservice.controller.payload;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

public class LoginResp {

    private String accessToken;
    
    public LoginResp(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
