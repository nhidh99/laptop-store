package org.example.model.input

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginInput(
    @JsonProperty("username")
    var username: String?,

    @JsonProperty("password")
    var password: String?
)