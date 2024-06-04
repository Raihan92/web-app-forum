package com.upwork.webforumapp.dto.thread;

import com.upwork.webforumapp.dto.ApiResponse;

import java.util.ArrayList;
import java.util.List;

public class ReadAllThreadsResponseDto extends ApiResponse {
    private List<ThreadResponseDto> threads;

    public ReadAllThreadsResponseDto() {
    }

    public ReadAllThreadsResponseDto(List<ThreadResponseDto> threads) {
        this.threads = threads;
    }

    public List<ThreadResponseDto> getThreads() {
        return threads;
    }

    public void setThreads(List<ThreadResponseDto> threads) {
        this.threads = threads;
    }

    public void addThreads(ThreadResponseDto responseDto) {
        if(threads == null) {
            threads = new ArrayList<>();
        }
        threads.add(responseDto);
    }
}
