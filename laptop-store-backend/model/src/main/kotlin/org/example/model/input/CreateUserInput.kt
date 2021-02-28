package org.example.model.input

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.apache.commons.lang3.StringUtils
import org.example.annotation.EnumPattern
import org.example.constant.RegexConstants
import org.example.model.type.Gender
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

class CreateUserInput {
    @field:JsonProperty(value = "username")
    @field:Size(min = 8, max = 20)
    @field:Pattern(regexp = RegexConstants.REGISTRATION_USERNAME)
    @field:Schema(description = "Username of registration", example = "username")
    var username: String? = null

    @field:JsonProperty(value = "password")
    @field:Size(min = 8, max = 30)
    @field:Pattern(regexp = RegexConstants.REGISTRATION_PASSWORD)
    @field:Schema(description = "Password of registration", example = "Password1@")
    var password: String? = null

    @field:JsonProperty(value = "confirm")
    @field:Size(min = 8, max = 30)
    @field:Schema(description = "Confirm password of registration", example = "Password1@")
    @field:Pattern(regexp = RegexConstants.REGISTRATION_PASSWORD)
    var confirm: String? = null

    @field:JsonProperty("name")
    @field:Size(min = 2, max = 30)
    @field:Schema(description = "User full name of registration", example = "User Full Name")
    @field:Pattern(regexp = RegexConstants.REGISTRATION_NAME)
    var name: String? = null
        set(value) {
            field = StringUtils.normalizeSpace(value)
        }

    @field:JsonProperty("phone")
    @field:Size(min = 10, max = 10)
    @field:Schema(description = "User phone number of registration", example = "0123456789")
    @field:Pattern(regexp = RegexConstants.REGISTRATION_PHONE)
    var phone: String? = null

    @field:JsonProperty("email")
    @field:Size(min = 4, max = 100)
    @field:Schema(description = "User email of registration", example = "test@example.com")
    @field:Pattern(regexp = RegexConstants.REGISTRATION_EMAIL)
    var email: String? = null

    @field:JsonProperty("gender")
    @field:EnumPattern(regexp = RegexConstants.REGISTRATION_GENDER)
    @field:Schema(description = "User gender of registration", example = "MALE")
    var gender: Gender? = null
}