package com.upwork.webforumapp.service;

import com.upwork.webforumapp.common.MessageStrings;
import com.upwork.webforumapp.dto.ApiResponse;
import com.upwork.webforumapp.dto.user.SignInDto;
import com.upwork.webforumapp.dto.user.SignInResponseDto;
import com.upwork.webforumapp.exceptions.AuthFailException;
import com.upwork.webforumapp.exceptions.CustomException;
import com.upwork.webforumapp.model.AuthToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.upwork.webforumapp.dto.user.SignUpResponseDto;
import com.upwork.webforumapp.dto.user.SignupDto;
import com.upwork.webforumapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import com.upwork.webforumapp.model.User;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthService authService;

    public SignUpResponseDto signUp(SignupDto signupDto) throws Exception {
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            throw new CustomException("User already exists");
        }
        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("Hashing failed: {}", e.getMessage());
        }

        User user = new User(signupDto.getUsername(), encryptedPassword, signupDto.getEmail());
        try {
            userRepository.save(user);
            final AuthToken authToken = new AuthToken(user);
            authService.saveToken(authToken);
            return new SignUpResponseDto("success", "Success!");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }

    public SignInResponseDto login(SignInDto signInDto) throws Exception {
        User user = userRepository.findByUsername(signInDto.getUsername());
        if(!Objects.nonNull(user)){
            throw new AuthFailException("user not present");
        }
        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                throw new AuthFailException(MessageStrings.WRONG_PASSWORD);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("Hashing failed: {}", e.getMessage());
            throw new CustomException(e.getMessage());
        }

        AuthToken token = authService.getToken(user);

        if(!Objects.nonNull(token)) {
            throw new CustomException(MessageStrings.AUTH_TOEKN_NOT_PRESENT);
        }

        return new SignInResponseDto ("success", "Success!", token.getToken());
    }

    public ApiResponse saveUsersFromCsv(BufferedReader reader) throws IOException {
        List<User> users = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            User user = new User(data[0], data[1], data[2]);
            users.add(user);
        }
        userRepository.saveAll(users);
        return new ApiResponse("success", "Success!");
    }
}
