package org.example.validator.auth;

import org.example.model.entity.user.User;
import org.example.model.request.LoginRequest;
import org.example.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public record AuthValidatorImpl(UserRepository userRepository) implements AuthValidator {
    @Override
    public boolean validateLoginInput(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.username());
        return user != null && BCrypt.checkpw(loginRequest.password(), user.getPassword());
    }
}