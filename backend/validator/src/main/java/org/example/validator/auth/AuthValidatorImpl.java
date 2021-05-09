package org.example.validator.auth;

import org.example.model.entity.user.User;
import org.example.model.projection.user.UserCredential;
import org.example.model.request.login.LoginRequest;
import org.example.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public record AuthValidatorImpl(UserRepository userRepository) implements AuthValidator {
    @Override
    public boolean validateLoginInput(LoginRequest loginRequest) {
        UserCredential user = userRepository.findByUsername(loginRequest.username(), UserCredential.class);
        return user != null && BCrypt.checkpw(loginRequest.password(), user.getPassword());
    }
}