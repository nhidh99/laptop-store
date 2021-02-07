package org.example.model.response

data class LoginResponse(
    var accessToken: String,
    var refreshToken: String
)