package org.example.service.auth.facade;

import org.example.model.projection.login.LoginResponse;
import org.example.model.request.login.LoginRequest;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
