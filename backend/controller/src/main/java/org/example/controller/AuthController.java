package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.constant.HeaderConstants;
import org.example.exception.InvalidCredentialException;
import org.example.model.request.login.FacebookLoginRequest;
import org.example.model.request.user.CreateUserRequest;
import org.example.model.request.login.LoginRequest;
import org.example.model.projection.login.LoginResponse;
import org.example.service.auth.facade.AuthService;
import org.example.service.user.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    private ResponseEntity<?> getLoginHttpResponse(LoginResponse response) {
        HttpHeaders headers = new HttpHeaders() {{
            add(HeaderConstants.ACCESS_TOKEN, response.getAccessToken());
            add(HeaderConstants.REFRESH_TOKEN, response.getRefreshToken());
        }};
        return ResponseEntity.noContent().headers(headers).build();
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) throws InvalidCredentialException {
        LoginResponse response = authService.login(request);
        return getLoginHttpResponse(response);
    }

    @PostMapping(value = "/login/facebook", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> loginByFacebook(@RequestBody FacebookLoginRequest request) throws InvalidCredentialException {
        LoginResponse response = authService.loginByFacebook(request);
        return getLoginHttpResponse(response);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> register(@RequestBody CreateUserRequest request) {
        userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
