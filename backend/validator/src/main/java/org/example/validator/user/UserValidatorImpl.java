package org.example.validator.user;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserValidatorImpl implements UserValidator {
    private final UserRepository userRepository;

    @Autowired
    public UserValidatorImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean registeredEmailExists(String email) {
        log.info("Received existing registered email: " + email);
        return userRepository.existsByRecordStatusTrueAndDetailVerifiedEmail(email);
    }

    @Override
    public boolean registeredUsernameExists(String username) {
        log.info("Received existing registered username: " + username);
        return userRepository.existsByRecordStatusTrueAndUsername(username);
    }

    @Override
    public boolean isPasswordAndConfirmMatched(String password, String confirm) {
        log.info("Password and Confirm is matched");
        return !StringUtils.equals(password, confirm);
    }
}
