package org.example.service.auth;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.InvalidCredentialException;
import org.example.model.request.LoginRequest;
import org.example.model.response.LoginResponse;
import org.example.security.JwtProvider;
import org.example.validator.auth.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    private final AuthValidator authValidator;
    private final JwtProvider jwtProvider;

    @Autowired
    public LoginServiceImpl(AuthValidator authValidator, JwtProvider jwtProvider) {
        this.authValidator = authValidator;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public LoginResponse execute(LoginRequest loginRequest) throws InvalidCredentialException {
        if (isInvalidCredential(loginRequest)) {
            log.info("Received invalid credential from user " + loginRequest.username());
            throw new InvalidCredentialException();
        }
        Pair<String, String> tokenPair = jwtProvider.getAccessAndRefreshTokens(loginRequest.username());
        return LoginResponse.fromTokenPair(tokenPair);
    }

    private boolean isInvalidCredential(LoginRequest loginRequest) {
        return !authValidator.validateLoginInput(loginRequest);
    }
}
