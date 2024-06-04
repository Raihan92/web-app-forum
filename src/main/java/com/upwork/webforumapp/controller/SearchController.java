package com.upwork.webforumapp.controller;

import com.upwork.webforumapp.dto.thread.ReadThreadResponseDto;
import com.upwork.webforumapp.dto.thread.SearchThreadDto;
import com.upwork.webforumapp.service.AuthService;
import com.upwork.webforumapp.service.ThreadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SearchController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ThreadsService threadsService;

    @GetMapping("/search")
    public ResponseEntity<?> getPostsByThreadId(@RequestHeader("session") String token,
                                                @RequestParam("text") String text) throws Exception {
        authService.authenticate(token);
        SearchThreadDto dto = threadsService.searchItems(text);
        HttpHeaders headers = authService.getHeader(token);
        return new ResponseEntity<>(dto, headers, HttpStatus.OK);
    }
}
