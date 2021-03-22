package org.example.validator.user;

public interface UserValidator {
    boolean registeredEmailExists(String email);
    boolean registeredUsernameExists(String username);
    boolean isPasswordAndConfirmMatched(String password, String confirm);
}
