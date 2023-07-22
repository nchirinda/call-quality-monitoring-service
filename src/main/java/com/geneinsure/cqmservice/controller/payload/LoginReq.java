package com.geneinsure.cqmservice.controller.payload;

import javax.validation.constraints.NotBlank;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

public class LoginReq {

    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
