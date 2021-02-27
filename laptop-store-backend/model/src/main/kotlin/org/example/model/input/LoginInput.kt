package org.example.model.input

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginInput(
    @JsonProperty("username")
    val username: String?,

    @JsonProperty("password")
    val password: String?
)