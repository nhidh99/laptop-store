package org.example.model.input

import com.fasterxml.jackson.annotation.JsonProperty
import org.example.constant.RegexConstants
import org.example.model.type.Gender
import javax.validation.constraints.*

data class RegistrationInput(
    @JsonProperty("username")
    @Pattern(regexp = RegexConstants.REGISTRATION_USERNAME)
    var username: String,

    @JsonProperty("password")
    @Pattern(regexp = RegexConstants.REGISTRATION_PASSWORD)
    var password: String,

    @JsonProperty("confirm")
    @Pattern(regexp = RegexConstants.REGISTRATION_PASSWORD)
    var confirm: String,

    @JsonProperty("name")
    @Pattern(regexp = RegexConstants.REGISTRATION_NAME)
    var name: String,

    @JsonProperty("gender")
    @NotNull
    var gender: Gender,

    @JsonProperty("phone")
    @Pattern(regexp = RegexConstants.REGISTRATION_PHONE)
    var phone: String,

    @JsonProperty("email")
    @Pattern(regexp = RegexConstants.REGISTRATION_EMAIL)
    var email: String,
)