package com.upwork.webforumapp.controller;

import com.upwork.webforumapp.dto.user.SignInDto;
import com.upwork.webforumapp.dto.user.SignInResponseDto;
import com.upwork.webforumapp.dto.user.SignUpResponseDto;
import com.upwork.webforumapp.dto.user.SignupDto;
import com.upwork.webforumapp.service.AuthService;
import com.upwork.webforumapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignupDto signupDto) throws Exception {
        SignUpResponseDto dto = userService.signUp(signupDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInDto signinDto) throws Exception {
        SignInResponseDto dto = userService.login(signinDto);
        HttpHeaders headers = authService.getHeader(dto.getToken());
        return new ResponseEntity<>(dto, headers, HttpStatus.OK);
    }
}
