package org.example.service.user

import org.example.model.input.CreateUserInput
import org.springframework.stereotype.Service

@Service
class CreateUserServiceImpl : CreateUserService {
    override fun execute(createUserInput: CreateUserInput) {
        println(createUserInput.name)
    }
}
