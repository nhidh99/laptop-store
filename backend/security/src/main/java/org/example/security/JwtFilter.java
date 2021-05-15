package org.example.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.example.constant.HeaderConstants;
import org.example.model.projection.login.LoginResponse;
import org.example.util.TranslatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    private static final String REFRESH_TOKEN_ENDPOINT = "/api/auth/token";

    @Autowired
    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        SecurityContextHolder.clearContext();
        String accessToken = jwtProvider.resolveToken(httpServletRequest);
        try {
            if (accessToken != null && jwtProvider.validateToken(accessToken)) {
                Authentication auth = jwtProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (ExpiredJwtException e) {
            String refreshToken = httpServletRequest.getHeader(HeaderConstants.REFRESH_TOKEN);
            boolean isValidRefreshRequest = refreshToken != null
                    && httpServletRequest.getRequestURI().equals(REFRESH_TOKEN_ENDPOINT)
                    && httpServletRequest.getMethod().equals(HttpMethod.POST.name());

            if (isValidRefreshRequest) {
                String username = e.getClaims().getSubject();
                sendRefreshTokens(username, refreshToken, httpServletResponse);
            } else {
                String message = TranslatorUtil.toLocale("session_expired_message");
                httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), message);
            }
            return;
        } catch (AuthenticationException e) {
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), e.getMessage());
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void sendRefreshTokens(String username, String refreshToken, HttpServletResponse httpServletResponse) throws IOException {
        Authentication auth = jwtProvider.getAuthentication(refreshToken);
        boolean isValidRequest = username.equalsIgnoreCase(((UserDetails) auth.getPrincipal()).getUsername());
        if (isValidRequest) {
            LoginResponse response = jwtProvider.getAccessAndRefreshTokens(username);
            httpServletResponse.setHeader(HeaderConstants.ACCESS_TOKEN, response.getAccessToken());
            httpServletResponse.setHeader(HeaderConstants.REFRESH_TOKEN, response.getRefreshToken());
            httpServletResponse.setStatus(HttpStatus.CREATED.value());
        } else {
            String message = TranslatorUtil.toLocale("invalid_token_message");
            httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), message);
        }
    }
}