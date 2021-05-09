package org.example.model.request.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.v3.oas.annotations.media.Schema;
import org.example.annotation.EnumPattern;
import org.example.constant.RegexConstants;
import org.example.deserializer.NormalizeSpaceDeserializer;
import org.example.model.type.Gender;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record CreateUserRequest(
    @JsonProperty(value = "username")
    @Size(min = 8, max = 20)
    @Pattern(regexp = RegexConstants.REGISTRATION_USERNAME)
    @Schema(description = "Username of registration", example = "username")
    String username,

    @JsonProperty(value = "password")
    @Size(min = 8, max = 30)
    @Pattern(regexp = RegexConstants.REGISTRATION_PASSWORD)
    @Schema(description = "Password of registration", example = "Password1@")
    String password,

    @JsonProperty(value = "confirm")
    @Size(min = 8, max = 30)
    @Schema(description = "Confirm password of registration", example = "Password1@")
    @Pattern(regexp = RegexConstants.REGISTRATION_PASSWORD)
    String confirm,

    @JsonProperty("name")
    @JsonDeserialize(using = NormalizeSpaceDeserializer.class)
    @Size(min = 2, max = 30)
    @Schema(description = "User full name of registration", example = "User Full Name")
    @Pattern(regexp = RegexConstants.REGISTRATION_NAME)
    String name,

    @JsonProperty("phone")
    @Size(min = 10, max = 10)
    @Schema(description = "User phone number of registration", example = "0123456789")
    @Pattern(regexp = RegexConstants.REGISTRATION_PHONE)
    String phone,

    @JsonProperty("email")
    @Size(min = 4, max = 100)
    @Schema(description = "User email of registration", example = "test@example.com")
    @Pattern(regexp = RegexConstants.REGISTRATION_EMAIL)
    String email,

    @JsonProperty("gender")
    @EnumPattern(regexp = RegexConstants.REGISTRATION_GENDER)
    @Schema(description = "User gender of registration", example = "MALE")
    Gender gender
) {
}