package com.upwork.webforumapp.service;

import com.upwork.webforumapp.common.MessageStrings;
import com.upwork.webforumapp.exceptions.AuthFailException;
import com.upwork.webforumapp.model.AuthToken;
import com.upwork.webforumapp.model.User;
import com.upwork.webforumapp.repository.AuthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    AuthRepository authRepository;

    private static String auth_admin_key;

    @Value("${admin_api_key}")
    public void setNameStatic(String admin_api_key){
        this.auth_admin_key = admin_api_key;
    }

    public void saveToken(AuthToken authenticationToken) {
        authRepository.save(authenticationToken);
    }

    public AuthToken getToken(User user) {
        return authRepository.findTokenByUser(user);
    }

    public User getUser(String token) {
        AuthToken authToken = authRepository.findTokenByToken(token);
        if (Objects.nonNull(authToken)) {
            if (Objects.nonNull(authToken.getUser())) {
                return authToken.getUser();
            }
        }
        return null;
    }

    public void authenticate(String token) throws AuthFailException {
        if (!Objects.nonNull(token)) {
            throw new AuthFailException(MessageStrings.AUTH_TOEKN_NOT_PRESENT);
        }
        if (!Objects.nonNull(getUser(token))) {
            throw new AuthFailException(MessageStrings.AUTH_TOEKN_NOT_VALID);
        }
    }

    public HttpHeaders getHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Set-Cookie","session="+token);
        return headers;
    }

    public boolean validateAdministrator(String token) throws AuthFailException {
        if(!token.equals(auth_admin_key)) {
            throw new AuthFailException(MessageStrings.AUTH_TOEKN_NOT_VALID);
        }
        return true;
    }
}
