package org.example.service.auth

import org.example.exception.InvalidCredentialException
import org.example.model.input.LoginInput
import org.example.model.response.LoginResponse
import org.example.security.JwtProvider
import org.example.validator.auth.AuthValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl @Autowired constructor(
    private val authValidator: AuthValidator,
    private val jwtProvider: JwtProvider
) : AuthService {
    override fun login(loginInput: LoginInput): LoginResponse {
        if (isInvalidLoginInput(loginInput)) {
            throw InvalidCredentialException()
        }
        val username = loginInput.username!!
        val (accessToken, refreshToken) = jwtProvider.getAccessAndRefreshTokens(username)
        return LoginResponse(accessToken = accessToken, refreshToken = refreshToken)
    }

    private fun isInvalidLoginInput(loginInput: LoginInput): Boolean {
        return !authValidator.validateLoginInput(loginInput)
    }
}