package org.example.model.projection.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Pair;

@Getter @Setter
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
}
