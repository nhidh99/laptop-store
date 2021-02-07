package org.example.validator.auth

import org.example.model.input.LoginInput
import org.example.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service


@Service
class AuthValidatorImpl @Autowired constructor(private val userRepository: UserRepository) : AuthValidator {
    override fun isValidLoginInput(loginInput: LoginInput): Boolean {
        val (username, password) = loginInput
        val user = userRepository.findByUsername(username)
        return user != null && BCrypt.checkpw(password, user.password)
    }
}