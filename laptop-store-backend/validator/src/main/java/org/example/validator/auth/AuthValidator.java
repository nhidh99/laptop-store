package org.example.validator.auth;

import org.example.model.request.LoginRequest;

public interface AuthValidator {
    boolean validateLoginInput(LoginRequest loginRequest);
}
