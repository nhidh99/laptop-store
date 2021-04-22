package org.example.controller;

import org.example.constant.HeaderConstants;
import org.example.exception.InvalidCredentialException;
import org.example.model.request.CreateUserRequest;
import org.example.model.request.LoginRequest;
import org.example.model.response.LoginResponse;
import org.example.service.auth.LoginService;
import org.example.service.user.CreateUserService;
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
public class AuthController {
    private final LoginService loginService;
    private final CreateUserService createUserService;

    @Autowired
    public AuthController(LoginService loginService, CreateUserService createUserService) {
        this.loginService = loginService;
        this.createUserService = createUserService;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws InvalidCredentialException {
        LoginResponse tokens = loginService.execute(loginRequest);
        HttpHeaders headers = new HttpHeaders() {{
            add(HeaderConstants.ACCESS_TOKEN, tokens.getAccessToken());
            add(HeaderConstants.REFRESH_TOKEN, tokens.getRefreshToken());
        }};
        return ResponseEntity.noContent().headers(headers).build();
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> register(@RequestBody CreateUserRequest input) {
        createUserService.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
