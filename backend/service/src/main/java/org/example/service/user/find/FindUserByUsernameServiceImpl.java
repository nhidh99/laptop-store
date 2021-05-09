package org.example.service.user.find;

import org.example.model.projection.user.UserResponse;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindUserByUsernameServiceImpl implements FindUserByUsernameService {
    private final UserRepository userRepository;

    @Autowired
    public FindUserByUsernameServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse process(String username) {
        return userRepository.findByUsername(username, UserResponse.class);
    }
}
