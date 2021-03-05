package org.example.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.example.annotation.EnumPattern;
import org.example.constant.RegexConstants;
import org.example.model.type.Gender;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter @Setter
public class CreateUserInput {
    @JsonProperty(value = "username")
    @Size(min = 8, max = 20)
    @Pattern(regexp = RegexConstants.REGISTRATION_USERNAME)
    @Schema(description = "Username of registration", example = "username")
    private String username;

    @JsonProperty(value = "password")
    @Size(min = 8, max = 30)
    @Pattern(regexp = RegexConstants.REGISTRATION_PASSWORD)
    @Schema(description = "Password of registration", example = "Password1@")
    private String password;

    @JsonProperty(value = "confirm")
    @Size(min = 8, max = 30)
    @Schema(description = "Confirm password of registration", example = "Password1@")
    @Pattern(regexp = RegexConstants.REGISTRATION_PASSWORD)
    private String confirm;

    @JsonProperty("name")
    @Size(min = 2, max = 30)
    @Schema(description = "User full name of registration", example = "User Full Name")
    @Pattern(regexp = RegexConstants.REGISTRATION_NAME)
    private String name;

    @JsonProperty("phone")
    @Size(min = 10, max = 10)
    @Schema(description = "User phone number of registration", example = "0123456789")
    @Pattern(regexp = RegexConstants.REGISTRATION_PHONE)
    private String phone;

    @JsonProperty("email")
    @Size(min = 4, max = 100)
    @Schema(description = "User email of registration", example = "test@example.com")
    @Pattern(regexp = RegexConstants.REGISTRATION_EMAIL)
    private String email;

    @JsonProperty("gender")
    @EnumPattern(regexp = RegexConstants.REGISTRATION_GENDER)
    @Schema(description = "User gender of registration", example = "MALE")
    private Gender gender;
}
