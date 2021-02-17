package org.example.validator.auth

import org.example.model.input.LoginInput

interface AuthValidator {
    fun validateLoginInput(loginInput : LoginInput) : Boolean
}