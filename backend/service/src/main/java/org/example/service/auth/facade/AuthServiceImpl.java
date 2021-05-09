package org.example.service.auth.facade;

import org.example.model.projection.login.LoginResponse;
import org.example.model.request.login.LoginRequest;
import org.example.service.auth.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final LoginService loginService;

    @Autowired
    public AuthServiceImpl(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        return loginService.execute(request);
    }
}
