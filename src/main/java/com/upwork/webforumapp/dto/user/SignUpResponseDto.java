package com.upwork.webforumapp.dto.user;

import com.upwork.webforumapp.dto.ApiResponse;

public class SignUpResponseDto extends ApiResponse {

    public SignUpResponseDto() {
        super();
    }

    public SignUpResponseDto(String status, String message) {
        super(status, message);
    }
}
