package org.example.controller

import io.swagger.v3.oas.annotations.media.Schema
import org.example.constant.HeaderConstants
import org.example.model.input.CreateUserInput
import org.example.model.input.LoginInput
import org.example.service.auth.LoginService
import org.example.service.user.CreateUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping("/api/auth")
class AuthController @Autowired constructor(
    private val loginService: LoginService,
    private val createUserService : CreateUserService
) {
    @PostMapping(value = ["/login"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun postLogin(@RequestBody loginInput: LoginInput): ResponseEntity<Void> {
        val (accessToken, refreshToken) = loginService.execute(loginInput)
        val headers: HttpHeaders = object : HttpHeaders() {
            init {
                add(HeaderConstants.ACCESS_TOKEN, accessToken)
                add(HeaderConstants.REFRESH_TOKEN, refreshToken)
            }
        }
        return ResponseEntity.noContent().headers(headers).build()
    }

    @PostMapping(value = ["/register"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Schema(description = "Registration")
    fun postRegistration(@Valid @RequestBody createUserInput: CreateUserInput): ResponseEntity<Void> {
        createUserService.execute(createUserInput)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}