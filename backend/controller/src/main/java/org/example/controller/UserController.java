package org.example.controller;

import org.example.constant.HeaderConstants;
import org.example.model.projection.user.UserResponse;
import org.example.service.user.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("permitAll()")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getByUsername(@PathVariable("username") String username) {
        UserResponse response = userService.findUserByUsername(username);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value="", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getAllUsers(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        return ResponseEntity.ok().header(HeaderConstants.TOTAL_COUNT, "0").body(Collections.emptyList());
    }
}
