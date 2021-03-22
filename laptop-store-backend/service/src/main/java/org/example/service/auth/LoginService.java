package org.example.service.auth;

import org.example.exception.InvalidCredentialException;
import org.example.model.request.LoginRequest;
import org.example.model.response.LoginResponse;

public interface LoginService {
    LoginResponse execute(LoginRequest loginRequest) throws InvalidCredentialException;
}
