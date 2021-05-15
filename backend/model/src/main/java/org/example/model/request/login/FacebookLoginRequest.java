package org.example.model.request.login;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record FacebookLoginRequest(
    @JsonProperty("name") String name,
    @JsonProperty("email") String email,
    @JsonProperty("userID") String userId
) {}