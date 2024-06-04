package com.upwork.webforumapp.controller;

import com.upwork.webforumapp.dto.ApiResponse;
import com.upwork.webforumapp.exceptions.CustomException;
import com.upwork.webforumapp.service.AuthService;
import com.upwork.webforumapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@CrossOrigin
public class FileUploadController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/csv")
    public ResponseEntity<?> uploadCsv(@RequestHeader("session") String token,
            @RequestParam("file") MultipartFile file) throws Exception {
        authService.validateAdministrator(token);
        if (file.isEmpty()) {
            throw new CustomException("400 Bad Request");
        }
        ApiResponse dto;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            dto = userService.saveUsersFromCsv(reader);
        } catch (Exception e) {
            throw new CustomException("400 Bad Request");
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
