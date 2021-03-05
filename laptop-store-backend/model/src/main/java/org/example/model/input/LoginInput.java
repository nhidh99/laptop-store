package org.example.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginInput {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}