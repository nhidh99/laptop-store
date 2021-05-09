package org.example.service.user.facade;

import org.example.model.projection.user.UserResponse;
import org.example.model.request.user.CreateUserRequest;
import org.example.service.user.create.CreateUserService;
import org.example.service.user.find.FindUserByUsernameService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final CreateUserService createUserService;
    private final FindUserByUsernameService findUserByUsernameService;

    public UserServiceImpl(CreateUserService createService, FindUserByUsernameService findByUsernameService) {
        this.createUserService = createService;
        this.findUserByUsernameService = findByUsernameService;
    }

    @Override
    public void createUser(CreateUserRequest request) {
        createUserService.execute(request);
    }

    @Override
    public UserResponse findUserByUsername(String username) {
        return findUserByUsernameService.execute(username);
    }
}
