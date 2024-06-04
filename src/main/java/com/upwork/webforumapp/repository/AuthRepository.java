package com.upwork.webforumapp.repository;

import com.upwork.webforumapp.model.AuthToken;
import com.upwork.webforumapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthToken, Integer> {
    AuthToken findTokenByUser(User user);
    AuthToken findTokenByToken(String token);
}
