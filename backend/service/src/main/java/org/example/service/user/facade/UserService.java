package org.example.service.user.facade;

import org.example.model.projection.user.UserResponse;
import org.example.model.request.user.CreateUserRequest;

public interface UserService {
    void createUser(CreateUserRequest request);
    UserResponse findUserByUsername(String username);
}
