package org.example.service.auth.login.facebook;

import org.example.constant.HeaderConstants;
import org.example.exception.InvalidCredentialException;
import org.example.model.entity.user.User;
import org.example.model.projection.login.LoginResponse;
import org.example.model.projection.user.UsernameValue;
import org.example.model.request.login.FacebookLoginRequest;
import org.example.repository.UserRepository;
import org.example.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.servlet.http.HttpServletRequest;

@Service
public class FacebookLoginServiceImpl implements FacebookLoginService {
    @Value("org.example.security.facebook.app-uri")
    private String facebookBaseUrl;

    @Value("org.example.security.facebook.app-id")
    private String facebookAppId;

    @Value("org.example.security.facebook.app-token")
    private String facebookAppSecret;

    @Value("org.example.security.facebook.app-token")
    private String facebookAppToken;

    private static final String DEBUG_URI = "/debug_token";
    private static final String INPUT_TOKEN = "input_token";
    private static final String ACCESS_TOKEN = "access_token";

    private final HttpServletRequest request;
    private final WebClient facebookClient;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Autowired
    public FacebookLoginServiceImpl(HttpServletRequest request, WebClient.Builder webClientBuilder,
                                    UserRepository userRepository, JwtProvider jwtProvider) {
        this.request = request;
        this.facebookClient = webClientBuilder.baseUrl(facebookBaseUrl).build();
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void validate(FacebookLoginRequest facebookLoginRequest) {
        try {
            String inputToken = request.getHeader(HeaderConstants.FACEBOOK_TOKEN);
            facebookClient.get().uri(uriBuilder -> uriBuilder
                    .path(DEBUG_URI).queryParam(INPUT_TOKEN, inputToken)
                    .queryParam(ACCESS_TOKEN, facebookAppToken).build()).retrieve();
        } catch (WebClientResponseException e) {
            throw new InvalidCredentialException();
        }
    }

    @Override
    public LoginResponse process(FacebookLoginRequest facebookLoginRequest) {
        String email = facebookLoginRequest.email();
        String username;

        if (userRepository.existsByVerifiedEmail(email)) {
            username = userRepository.findByVerifiedEmail(email, UsernameValue.class).getValue();
        } else {
            User user = User.fromFacebookLoginRequest(facebookLoginRequest);
            username = userRepository.saveAndFlush(user).getUsername();
        }
        return jwtProvider.getAccessAndRefreshTokens(username);
    }
}
