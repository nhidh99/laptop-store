package org.example.controller

import org.example.constant.HeaderConstants
import org.example.model.input.LoginInput
import org.example.service.auth.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/auth")
class AuthController @Autowired constructor(private val authService: AuthService) {
    @PostMapping(
        value = ["/login"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.TEXT_PLAIN_VALUE]
    )
    fun postLogin(@RequestBody loginInput: LoginInput): ResponseEntity<Void> {
        val (accessToken, refreshToken) = authService.login(loginInput)
        val headers: HttpHeaders = object : HttpHeaders() {
            init {
                add(HeaderConstants.ACCESS_TOKEN, accessToken)
                add(HeaderConstants.REFRESH_TOKEN, refreshToken)
            }
        }
        return ResponseEntity.noContent().headers(headers).build()
    }
}