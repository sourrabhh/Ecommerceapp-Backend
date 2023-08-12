package com.sourabh.ecommerceapp.Response;

public class AuthResponse 
{
    private String jwt;
    private String message;
    
    public AuthResponse(String jwt, String message) {
        this.jwt = jwt;
        this.message = message;
    }

    
}
