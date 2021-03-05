package org.example.validator.auth;

import org.example.model.entity.user.User;
import org.example.model.input.LoginInput;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthValidatorImpl implements AuthValidator {
    private final UserRepository userRepository;

    @Autowired
    public AuthValidatorImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean validateLoginInput(LoginInput loginInput) {
        User user = userRepository.findByUsername(loginInput.getUsername());
        return user != null && BCrypt.checkpw(loginInput.getPassword(), user.getPassword());
    }
}
