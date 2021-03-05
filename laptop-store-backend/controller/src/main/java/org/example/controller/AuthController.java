package org.example.controller;

import org.apache.commons.lang3.tuple.Pair;
import org.example.constant.HeaderConstants;
import org.example.exception.InvalidCredentialException;
import org.example.model.input.CreateUserInput;
import org.example.model.input.LoginInput;
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
    public ResponseEntity<?> login(@RequestBody LoginInput loginInput) throws InvalidCredentialException {
        LoginResponse tokens = loginService.execute(loginInput);
        HttpHeaders headers = new HttpHeaders() {{
            add(HeaderConstants.ACCESS_TOKEN, tokens.getAccessToken());
            add(HeaderConstants.REFRESH_TOKEN, tokens.getRefreshToken());
        }};
        return ResponseEntity.noContent().headers(headers).build();
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> register(@RequestBody CreateUserInput input) {
        createUserService.execute(input);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}