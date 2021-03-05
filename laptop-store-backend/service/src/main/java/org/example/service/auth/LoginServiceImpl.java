package org.example.service.auth;

import org.example.exception.InvalidCredentialException;
import org.example.model.input.LoginInput;
import org.example.model.response.LoginResponse;
import org.example.security.JwtProvider;
import org.example.validator.auth.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final AuthValidator authValidator;
    private final JwtProvider jwtProvider;

    @Autowired
    public LoginServiceImpl(AuthValidator authValidator, JwtProvider jwtProvider) {
        this.authValidator = authValidator;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public LoginResponse execute(LoginInput loginInput) throws InvalidCredentialException {
        if (isInvalidCredential(loginInput)) {
            throw new InvalidCredentialException();
        }

        String username = loginInput.getUsername();
        Pair<String, String> tokens = jwtProvider.getAccessAndRefreshTokens(username);
        return new LoginResponse() {{
            setAccessToken(tokens.getFirst());
            setRefreshToken(tokens.getSecond());
        }};
    }

    private boolean isInvalidCredential(LoginInput loginInput) {
        return !authValidator.validateLoginInput(loginInput);
    }
}
