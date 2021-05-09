package org.example.model.projection.login;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Pair;

@Getter @Setter
public class LoginResponse {
    private String accessToken;
    private String refreshToken;

    public static LoginResponse fromTokenPair(Pair<String, String> tokens) {
        return new LoginResponse() {{
            setAccessToken(tokens.getFirst());
            setRefreshToken(tokens.getSecond());
        }};
    }
}
