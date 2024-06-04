package com.upwork.webforumapp.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.upwork.webforumapp.dto.ApiResponse;

public class SignInResponseDto extends ApiResponse {
    @JsonIgnore
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SignInResponseDto(String status, String message) {
        super(status, message);
    }

    public SignInResponseDto(String status, String message, String token) {
        super(status, message);
        this.token = token;
    }
}
