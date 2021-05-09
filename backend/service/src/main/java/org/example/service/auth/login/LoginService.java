package org.example.service.auth.login;

import org.example.exception.InvalidCredentialException;
import org.example.model.request.login.LoginRequest;
import org.example.model.projection.login.LoginResponse;
import org.example.service.base.ResponseService;

public interface LoginService extends ResponseService<LoginRequest, LoginResponse> {
}
