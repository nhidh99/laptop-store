package org.example.validator.auth;

import org.example.model.input.LoginInput;

public interface AuthValidator {
    boolean validateLoginInput(LoginInput loginInput);
}
