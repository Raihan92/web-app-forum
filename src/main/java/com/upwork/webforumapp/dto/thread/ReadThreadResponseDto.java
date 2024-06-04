package com.upwork.webforumapp.dto.thread;

import com.upwork.webforumapp.dto.ApiResponse;

public class ReadThreadResponseDto extends ApiResponse {
    private ThreadResponseDto threadResponseDto;

    public ReadThreadResponseDto() {
    }

    public ReadThreadResponseDto(ThreadResponseDto threadResponseDto) {
        this.threadResponseDto = threadResponseDto;
    }

    public ThreadResponseDto getThreadResponseDto() {
        return threadResponseDto;
    }

    public void setThreadResponseDto(ThreadResponseDto threadResponseDto) {
        this.threadResponseDto = threadResponseDto;
    }
}
