package org.example.service.auth.facade;

import org.example.model.projection.login.LoginResponse;
import org.example.model.request.login.FacebookLoginRequest;
import org.example.model.request.login.LoginRequest;
import org.example.service.auth.login.facebook.FacebookLoginService;
import org.example.service.auth.login.manual.ManualLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final ManualLoginService manualLoginService;
    private final FacebookLoginService facebookLoginService;

    @Autowired
    public AuthServiceImpl(ManualLoginService manualLoginService, FacebookLoginService facebookLoginService) {
        this.manualLoginService = manualLoginService;
        this.facebookLoginService = facebookLoginService;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        return manualLoginService.execute(request);
    }

    @Override
    public LoginResponse loginByFacebook(FacebookLoginRequest request) {
        return facebookLoginService.execute(request);
    }
}
