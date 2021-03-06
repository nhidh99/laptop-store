package org.example.security;

import io.jsonwebtoken.*;
import org.example.constant.TokenConstants;
import org.example.model.projection.login.LoginResponse;
import org.example.model.projection.user.UserRoleValue;
import org.example.model.type.UserRole;
import org.example.repository.UserRepository;
import org.example.util.TranslatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@Component
public class JwtProvider {
    @Value("${org.example.security.jwt.token.secret-key:LAPTOP_STORE}")
    private String secretKey;

    private final AppUserDetails appUserDetails;

    private final UserRepository userRepository;

    @Autowired
    public JwtProvider(AppUserDetails appUserDetails, UserRepository userRepository) {
        this.appUserDetails = appUserDetails;
        this.userRepository = userRepository;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String getAccessToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        UserRole role = userRepository.findByUsername(username, UserRoleValue.class).getValue();
        claims.put("auth", Collections.singletonList(new SimpleGrantedAuthority(role.getAuthority())));

        Date now = new Date();
        Date validity = new Date(now.getTime() + TokenConstants.ACCESS_TOKEN_EXPIRATION_IN_MILLISECOND);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getRefreshToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        Date validity = new Date(now.getTime() + TokenConstants.REFRESH_TOKEN_EXPIRATION_IN_MILLISECOND);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public LoginResponse getAccessAndRefreshTokens(String username) {
        String accessToken = getAccessToken(username);
        String refreshToken = getRefreshToken(username);
        return new LoginResponse(accessToken, refreshToken);
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = appUserDetails.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException | UnsupportedJwtException | SignatureException | IllegalArgumentException e) {
            String message = TranslatorUtil.toLocale("invalid_token_message");
            throw new AuthenticationException(message) {
            };
        }
    }
}
