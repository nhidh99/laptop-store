package org.example.service.auth

import org.example.model.input.LoginInput
import org.example.model.response.LoginResponse

interface AuthService {
    fun login(loginInput: LoginInput) : LoginResponse
}