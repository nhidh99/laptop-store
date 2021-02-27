package org.example.model.input

import com.fasterxml.jackson.annotation.JsonProperty
import org.example.constant.RegexConstants
import org.example.model.type.Gender
import javax.validation.constraints.*

data class CreateUserInput(
    @JsonProperty("username")
    @field:Pattern(regexp = RegexConstants.REGISTRATION_USERNAME)
    val username: String,

    @JsonProperty("password")
    @field:Pattern(regexp = RegexConstants.REGISTRATION_PASSWORD)
    val password: String,

    @JsonProperty("confirm")
    @field:Pattern(regexp = RegexConstants.REGISTRATION_PASSWORD)
    val confirm: String,

    @JsonProperty("name")
    @field:Pattern(regexp = RegexConstants.REGISTRATION_NAME)
    val name: String,

    @JsonProperty("gender")
    @field:NotNull
    val gender: Gender,

    @JsonProperty("phone")
    @field:Pattern(regexp = RegexConstants.REGISTRATION_PHONE)
    val phone: String,

    @JsonProperty("email")
    @field:Pattern(regexp = RegexConstants.REGISTRATION_EMAIL)
    val email: String,
)