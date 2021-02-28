package org.example.model.input

import com.fasterxml.jackson.annotation.JsonProperty

class LoginInput {
    @field:JsonProperty("username")
    var username: String? = null

    @field:JsonProperty("password")
    var password: String? = null
}