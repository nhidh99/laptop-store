package org.example.security

import io.jsonwebtoken.ExpiredJwtException
import org.example.constant.ErrorMessageConstants
import org.example.constant.HeaderConstants
import org.example.constant.PathnameConstants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtFilter @Autowired constructor(private val jwtProvider: JwtProvider) : OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        SecurityContextHolder.clearContext()
        val accessToken = jwtProvider.resolveToken(request)
        try {
            if (accessToken != null && jwtProvider.validateToken(accessToken)) {
                val auth = jwtProvider.getAuthentication(accessToken)
                SecurityContextHolder.getContext().authentication = auth
            }
        } catch (e: ExpiredJwtException) {
            val refreshToken = request.getHeader(HeaderConstants.REFRESH_TOKEN)
            if (refreshToken != null && isValidRefreshRequest(request)) {
                val username = e.claims.subject
                sendRefreshTokens(username, refreshToken, response)
            } else {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), ErrorMessageConstants.EXPIRED_TOKEN)
            }
            return
        } catch (e: AuthenticationException) {
            response.sendError(HttpStatus.FORBIDDEN.value(), e.message)
            return
        }
        filterChain.doFilter(request, response)
    }

    private fun isValidRefreshRequest(request: HttpServletRequest): Boolean {
        return request.requestURI == PathnameConstants.REFRESH_TOKEN && request.method == HttpMethod.POST.name
    }

    @Throws(IOException::class)
    private fun sendRefreshTokens(username: String, refreshToken: String, response: HttpServletResponse) {
        if (isValidRefreshToken(username, refreshToken)) {
            val tokens: Pair<String, String> = jwtProvider.getAccessAndRefreshTokens(username)
            response.setHeader(HeaderConstants.ACCESS_TOKEN, tokens.first)
            response.setHeader(HeaderConstants.REFRESH_TOKEN, tokens.second)
            response.status = HttpStatus.CREATED.value()
        } else {
            response.sendError(HttpStatus.FORBIDDEN.value(), ErrorMessageConstants.FORBIDDEN)
        }
    }

    private fun isValidRefreshToken(username: String, refreshToken: String): Boolean {
        val auth = jwtProvider.getAuthentication(refreshToken)
        return username.equals((auth.principal as UserDetails).username, ignoreCase = true)
    }
}