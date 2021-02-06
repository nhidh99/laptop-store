package org.example.security

import io.jsonwebtoken.*
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.Jwts
import org.example.constant.ErrorMessageConstants
import org.example.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.security.SignatureException
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest


@Component
class JwtProvider @Autowired constructor(
    private val appUserDetails: AppUserDetails,
    private val userRepository: UserRepository
) {
    @Value("\${org.example.security.jwt.token.secret-key:LAPTOP_STORE}")
    private var secretKey: String? = null

    @Value("\${org.example.security.jwt.token.access-token-expire-length:900000}")
    private val accessTokenExpiration: Long = 0

    @Value("\${org.example.security.jwt.token.refresh-token-expire-length:2592000000}")
    private val refreshTokenExpiration: Long = 0

    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey!!.toByteArray())
    }

    private fun getAccessToken(username: String): String {
        val claims = getAccessTokenClaims(username);
        val now = Date()
        val validity = Date(now.time + accessTokenExpiration)
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    private fun getAccessTokenClaims(username: String): Claims {
        val claims: Claims = Jwts.claims().setSubject(username)
        val role = userRepository.findRoleByUsername(username)
        claims["auth"] = listOf(SimpleGrantedAuthority(role.authority))
        return claims
    }

    private fun getRefreshToken(username: String?): String {
        val claims: Claims = Jwts.claims().setSubject(username)
        val now = Date()
        val validity = Date(now.time + refreshTokenExpiration)
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun getAccessAndRefreshTokens(username: String): Pair<String, String> {
        val accessToken = getAccessToken(username)
        val refreshToken = getRefreshToken(username)
        return Pair(accessToken, refreshToken)
    }

    fun getAuthentication(token: String): Authentication {
        val username = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject;
        val userDetails = appUserDetails.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken: String? = req.getHeader(HttpHeaders.AUTHORIZATION)
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else null
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            return true
        } catch (e: MalformedJwtException) {
            throw object : AuthenticationException(ErrorMessageConstants.FORBIDDEN) {}
        } catch (e: UnsupportedJwtException) {
            throw object : AuthenticationException(ErrorMessageConstants.FORBIDDEN) {}
        } catch (e: SignatureException) {
            throw object : AuthenticationException(ErrorMessageConstants.FORBIDDEN) {}
        } catch (e: IllegalArgumentException) {
            throw object : AuthenticationException(ErrorMessageConstants.FORBIDDEN) {}
        }
    }
}