package com.upwork.webforumapp.controller;

import com.upwork.webforumapp.dto.ApiResponse;
import com.upwork.webforumapp.dto.thread.CreateThreadPostsDto;
import com.upwork.webforumapp.dto.thread.ReadAllThreadsResponseDto;
import com.upwork.webforumapp.dto.thread.ReadThreadResponseDto;
import com.upwork.webforumapp.dto.thread.ThreadDto;
import com.upwork.webforumapp.service.AuthService;
import com.upwork.webforumapp.service.ThreadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/thread")
public class ThreadController {

    @Autowired
    private ThreadsService threadsService;

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<?> createThreads(@RequestHeader("session") String token,
                                           @RequestBody ThreadDto threadDto) throws Exception {
        authService.authenticate(token);
        ApiResponse dto = threadsService.createThread(threadDto);
        HttpHeaders headers = authService.getHeader(token);
        return new ResponseEntity<>(dto, headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getThreads (
            @RequestHeader("session") String token,
            @RequestParam List<String> categories,
            @RequestParam(name = "newest_first", defaultValue = "true") boolean newestFirst,
            @RequestParam int page,
            @RequestParam(name = "page_size") int pageSize) throws Exception {

        authService.authenticate(token);
        ReadAllThreadsResponseDto dto = threadsService.getThreadsByCriteria(categories, page, pageSize);
        HttpHeaders headers = authService.getHeader(token);
        return new ResponseEntity<>(dto, headers, HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<?> updateThreadByAddingPosts(@RequestHeader("session") String token,
                                                       @RequestBody CreateThreadPostsDto threadDto) throws Exception {
        authService.authenticate(token);
        ApiResponse dto = threadsService.updateThreadsById(threadDto);
        HttpHeaders headers = authService.getHeader(token);
        return new ResponseEntity<>(dto, headers, HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity<?> getPostsByThreadId(@RequestHeader("session") String token,
                                                @RequestParam("thread_id") Integer threadId) throws Exception {
        authService.authenticate(token);
        ReadThreadResponseDto dto = threadsService.getThreadsById(threadId);
        HttpHeaders headers = authService.getHeader(token);
        return new ResponseEntity<>(dto, headers, HttpStatus.OK);
    }

}
