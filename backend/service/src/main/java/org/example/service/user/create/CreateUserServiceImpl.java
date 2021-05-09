package org.example.service.user.create;

import lombok.extern.slf4j.Slf4j;
import org.example.model.entity.user.User;
import org.example.model.request.user.CreateUserRequest;
import org.example.repository.UserRepository;
import org.example.util.TranslatorUtil;
import org.example.validator.user.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateUserServiceImpl implements CreateUserService {
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Autowired
    public CreateUserServiceImpl(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    @Override
    public void validate(CreateUserRequest request) {
        if (!userValidator.isPasswordAndConfirmMatched(request.username(), request.confirm())) {
            throw new IllegalArgumentException(TranslatorUtil.toLocale("error-registered-passwords-mismatch"));
        }

        if (userValidator.registeredUsernameExists(request.username())) {
            throw new IllegalArgumentException(TranslatorUtil.toLocale("error-registered-username-existed"));
        }

        if (userValidator.registeredEmailExists(request.email())) {
            throw new IllegalArgumentException(TranslatorUtil.toLocale("error-registered-email-existed"));
        }
    }

    @Override
    public void process(CreateUserRequest request) {
        User user = User.fromCreateUserRequest(request);
        userRepository.save(user);
    }
}
