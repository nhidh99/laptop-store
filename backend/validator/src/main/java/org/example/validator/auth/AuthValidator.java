package org.example.validator.auth;

import org.example.model.request.login.LoginRequest;

public interface AuthValidator {
    boolean validateLoginInput(LoginRequest loginRequest);
}
