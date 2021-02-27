package org.example.service.user

import org.example.model.input.CreateUserInput

interface CreateUserService {
    fun execute(createUserInput: CreateUserInput)
}
