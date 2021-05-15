package org.example.service.auth.login.facebook;

import org.example.model.projection.login.LoginResponse;
import org.example.model.request.login.FacebookLoginRequest;
import org.example.service.base.ResponseService;

public interface FacebookLoginService extends ResponseService<FacebookLoginRequest, LoginResponse> {
}
