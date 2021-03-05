package org.example.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.exception.InvalidCredentialException;
import org.example.util.TranslatorUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler(value = InvalidCredentialException.class)
    public ResponseEntity<?> handleAuthenticationException(InvalidCredentialException e) {
        String message = TranslatorUtil.toLocale("invalid_credential_message");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleServerException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(value = {JsonProcessingException.class, Exception.class})
    public ResponseEntity<?> handleServerException(Exception e) {
        e.printStackTrace();
        String message = TranslatorUtil.toLocale("server_error_message");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}
