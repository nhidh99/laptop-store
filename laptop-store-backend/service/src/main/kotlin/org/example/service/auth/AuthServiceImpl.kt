package org.example.service.auth

import org.example.exception.InvalidCredentialException
import org.example.model.input.LoginInput
import org.example.model.response.LoginResponse
import org.example.repository.UserRepository
import org.example.security.JwtProvider
import org.example.validator.auth.AuthValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
@Service
class AuthServiceImpl @Autowired constructor(
    private val authValidator: AuthValidator,
    private val jwtProvider: JwtProvider
) : AuthService {
    override fun login(loginInput: LoginInput): LoginResponse {
        if (!authValidator.isValidLoginInput(loginInput)) {
            throw InvalidCredentialException()
        }
        val (accessToken, refreshToken) = jwtProvider.getAccessAndRefreshTokens(loginInput.username!!)
        return LoginResponse(accessToken = accessToken, refreshToken = refreshToken)
    }
}