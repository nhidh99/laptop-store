package org.example.service.auth;

import org.example.exception.InvalidCredentialException;
import org.example.model.input.LoginInput;
import org.example.model.response.LoginResponse;

public interface LoginService {
    LoginResponse execute(LoginInput loginInput) throws InvalidCredentialException;
}
