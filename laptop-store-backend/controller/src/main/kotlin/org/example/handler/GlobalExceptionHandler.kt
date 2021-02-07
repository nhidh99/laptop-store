package org.example.handler

import com.fasterxml.jackson.core.JsonProcessingException
import org.example.constant.ErrorMessageConstants
import org.example.exception.InvalidCredentialException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(value = [InvalidCredentialException::class])
    fun handleAuthenticationException(e: InvalidCredentialException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorMessageConstants.INVALID_CREDENTIAL)
    }

    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleServerException(e: MethodArgumentNotValidException): ResponseEntity<String> {
        val message = e.bindingResult.allErrors[0].defaultMessage
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message)
    }

    @ExceptionHandler(value = [JsonProcessingException::class, Exception::class])
    fun handleServerException(e: Exception): ResponseEntity<String> {
        e.printStackTrace()
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorMessageConstants.SERVER_ERROR)
    }
}